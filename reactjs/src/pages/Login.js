import React from "react";
import "./Login.css";
import Navbar from "../components/Navbar";
import LoginContainer from "../components/LoginContainer.js";

function Login() {
  return (
    <>
      <div className="desktop">
        <Navbar />
        <LoginContainer />
      </div>
      <div className="mobile">
        <LoginContainer />
      </div>
    </>
  );
}

export default Login;
