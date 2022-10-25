import React, { useEffect, useState } from "react";
import { Alert } from "@mui/material";
const AlertMsg = (pros) => {
	return pros.show ? <Alert severity={pros.type}>{pros.message}</Alert> : null;
};

export default AlertMsg;
