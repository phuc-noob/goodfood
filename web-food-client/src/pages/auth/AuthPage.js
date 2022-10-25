import LoginForm from "../../components/auth/LoginForm";
import RegisterForm from "../../components/auth/RegisterForm";
import { Navigate } from "react-router-dom";
import React, { useContext } from "react";
import { AuthContext } from "../../helpers/context/AuthContext";
import { Spinner } from "react-bootstrap";
import styled from "@emotion/styled";

function AuthPage({ authRoute }) {
	const {
		authState: { authLoading, isAuthenticated },
	} = useContext(AuthContext);
	let body;
	if (authLoading) {
		body = (
			<div className="d-flex justify-content-center mt-5">
				<Spinner animation="border" variant="info"></Spinner>;
			</div>
		);
	} else if (isAuthenticated) {
		return <Navigate to="/" replace />;
	} else {
		body = authRoute === "login" ? <LoginForm /> : <RegisterForm />;
	}
	return <Wrapper className="container-fluid">{body}</Wrapper>;
}
const Wrapper = styled.div`
	background-color: hsl(218, 41%, 15%);
	background-image: radial-gradient(
			650px circle at 0% 0%,
			hsl(218, 41%, 35%) 15%,
			hsl(218, 41%, 30%) 35%,
			hsl(218, 41%, 20%) 75%,
			hsl(218, 41%, 19%) 80%,
			transparent 100%
		),
		radial-gradient(
			1250px circle at 100% 100%,
			hsl(218, 41%, 45%) 15%,
			hsl(218, 41%, 30%) 35%,
			hsl(218, 41%, 20%) 75%,
			hsl(218, 41%, 19%) 80%,
			transparent 100%
		);
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	opacity: 0.8;
`;
export default AuthPage;
