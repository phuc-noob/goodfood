import { createSlice } from "@reduxjs/toolkit";

const initialState = {
	user: null,
	authLoading: true,
	isAuthenticated: false,
};

const authSlice = createSlice({
	name: "auth",
	initialState,
	reducers: {
		setAuth(state, action) {
			const { user, isAuthenticated } = action.payload;
			return {
				...state,
				authLoading: false,
				isAuthenticated,
				user,
			};
		},
		loadingAuth(state) {
			return {
				...state,
				authLoading: true,
			};
		},
		logout(state) {
			return {
				...state,
				authLoading: false,
				isAuthenticated: false,
				user: null,
			};
		},
		// setAlert(state, action) {
		// 	const { type, message } = action.payload;
		// 	return {
		// 		...state,
		// 		alertState: {
		// 			type,
		// 			message,
		// 		},
		// 	};
		// },
		// clearAlert(state) {
		// 	return {
		// 		...state,
		// 		alertState: null,
		// 	};
		// },
	},
});

export default authSlice.reducer;
export const authAction = authSlice.actions;
export const initAuthState = initialState;
