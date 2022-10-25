import React, { useContext, useState, useEffect } from "react";
import { Link } from "react-router-dom";
import styled from "@emotion/styled";
import { AuthContext } from "../../helpers/context/AuthContext";
import AlertMsg from "../layout/AlertMessage";
const RegisterForm = () => {
	const { registerUser } = useContext(AuthContext);
	const initState = {
		username: "",
		password: "",
		confirmPassword: "",
	};
	const [stateForm, setStateForm] = useState(initState);
	const onChangeForm = (e) => {
		setStateForm((pre) => {
			return {
				...pre,
				[e.target.name]: e.target.value,
			};
		});
	};

	const [alert, setAlert] = useState(null);
	useEffect(() => {
		if (alert) {
			let out = setTimeout(() => setAlert(null), 3000);
			console.log("tao");
			return () => {
				console.log("clear");
				clearTimeout(out);
			};
		}
	}, [alert]); // thay đổi  vùng nhớ mới khi submit thì sẽ reset timeout
	const onClickSubmit = async (e) => {
		const res = await registerUser(stateForm);
		if (res.success) {
			setAlert({ type: "primary", message: res.message });
			setStateForm(initState);
		} else {
			setAlert({ type: "danger", message: res.message });
		}
	};

	return (
		<div className="container-fluid row">
			<div
				className="col-sm-6 flex-column d-flex bg-white p-5"
				style={{ gap: "20px", minHeight: "600px" }}
			>
				<div className="d-flex justify-content-center">
					<Header>SIGN UP</Header>
				</div>
				<div>
					{alert && (
						<AlertMsg info={{ type: alert.type, message: alert.message }} />
					)}
				</div>
				<div className="form mt-2">
					<div className="form-floating mt-3 ">
						<input
							type="text"
							className="form-control"
							id="inputUsr"
							placeholder="Username"
							name="username"
							value={stateForm.username}
							onChange={onChangeForm}
						/>
						<label htmlFor="inputUsr">Username</label>
					</div>
					<div className="form-floating mt-3 ">
						<input
							type="password"
							className="form-control"
							id="inputPwd"
							placeholder="Password"
							name="password"
							value={stateForm.password}
							onChange={onChangeForm}
						/>
						<label htmlFor="inputPwd">Password</label>
					</div>
					<div className="form-floating mt-3 ">
						<input
							type="password"
							className="form-control"
							id="inputPwdConfirm"
							placeholder="inputPwdConfirm"
							name="confirmPassword"
							value={stateForm.confirmPassword}
							onChange={onChangeForm}
						/>
						<label htmlFor="inputPwdConfirm">Confirm Password</label>
					</div>
				</div>
				<div className="d-flex w-100 justify-content-end">
					<div>
						<Link to="/login">Have an account?</Link>
					</div>
				</div>
				<div className="mb-3">
					<button
						className="btn btn-primary w-100"
						style={{ height: "50px" }}
						onClick={onClickSubmit}
					>
						<span>
							<i className="fa fa-user-plus me-2" />
						</span>
						SIGN UP
					</button>
				</div>
			</div>
			<div className="col-sm-6 p-5">
				<Header className="text-white"> The best offer</Header>
				<Header className="text-info"> for your business</Header>
				<Paragram>
					Lorem ipsum dolor, sit amet consectetur adipisicing elit. Temporibus,
					expedita iusto veniam atque, magni tempora mollitia dolorum
					consequatur nulla, neque debitis eos reprehenderit quasi ab ipsum nisi
					dolorem modi. Quos?
				</Paragram>
			</div>
		</div>
	);
};
const Header = styled.h2`
	font-weight: 700;
	font-size: 45px;
	margin-top: 5px;
`;
const Paragram = styled.p`
	margin-top: 56px;
	margin-bottom: 56px;

	margin-right: 50px;
	color: hsl(218, 81%, 85%);
	text-align: justify;
`;
export default RegisterForm;
