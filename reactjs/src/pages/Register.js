import React from 'react';
import './Register.css';
import Navbar from "../components/Navbar";
import RegisterContainer from "../components/RegisterContainer";

function Register() {
  return (
    <div className="desktop">
        <Navbar />
        <RegisterContainer/>
       </div>
  );
}

export default Register;