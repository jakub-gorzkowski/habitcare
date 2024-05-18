import React from 'react';
import './Navbar.css';
import './LoginContainer.css';
import DynamicForm from './DynamicForm';
import {Link} from "react-router-dom";

const fieldsForLoginForm = [
    { type: 'email', name: 'email', placeholder: 'Email' },
    { type: 'password', name: 'password', placeholder: 'Password' },
];
const LoginContainer = () => {
    return (
        <div className="login-container">
            <p className="login-text">Log in to HabitCare</p>

            <DynamicForm fields={fieldsForLoginForm} buttonText="Login"/>
            <p className="no-acc-message">Don't have an account? <Link to="/register">Sign up</Link></p>
        </div>
    );
}

export default LoginContainer;