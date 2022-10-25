import authReducer, { initAuthState, authAction } from "../feature/authSlice";
import { createContext, useReducer, useEffect, useState } from "react";
import { authService } from "../service/authService";
export const AuthContext = createContext();

function AuthContextProvider({ children }) {
	const [authState, dispatch] = useReducer(authReducer, initAuthState);
	const [alertAuth, setAlertAuth] = useState({
		show: false,
		type: "error",
		message: "fail",
	});
	let timeout = null;
	const clearAlert = () => {
		timeout = null;
		setAlertAuth((pre) => {
			return { ...pre, show: false };
		});
	};
	const setAlertFail = ({ message }) => {
		if (timeout) {
			console.log("clear", timeout);
			clearTimeout(timeout);
		}
		setAlertAuth({
			show: true,
			type: "error",
			message,
		});
		timeout = setTimeout(() => {
			clearAlert();
		}, 3000);
	};

	const loginUser = async (userForm) => {
		dispatch(authAction.loadingAuth());
		try {
			const res = await authService.login(userForm);
			return res;
		} catch (err) {
			return err;
		} finally {
			loaddingUser();
		}
	};

	const loaddingUser = async () => {
		dispatch(authAction.loadingAuth());
		try {
			const res = await authService.loadUser();
			dispatch(
				authAction.setAuth({
					isAuthenticated: true,
					user: res.data,
				})
			);
			return res;
		} catch (err) {
			dispatch(
				authAction.setAuth({
					isAuthenticated: false,
					user: null,
				})
			);
			return err;
		}
	};
	const registerUser = async (userForm) => {
		authAction.loadingAuth();
		try {
			const res = await authService.register(userForm);
			return res;
		} catch (err) {
			return err;
		}
	};
	const logout = () => {
		authService.logout();
		dispatch(authAction.logout());
	};
	useEffect(() => {
		loaddingUser();
	}, []);
	const authContextData = {
		authState,
		dispatch,
		loginUser,
		loaddingUser,
		registerUser,
		logout,
		alertAuth,
		clearAlert,
		setAlertFail,
	};
	return (
		<AuthContext.Provider value={authContextData}>
			{children}
		</AuthContext.Provider>
	);
}

export default AuthContextProvider;
