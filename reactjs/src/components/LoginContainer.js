import React from 'react';
import './Navbar.css';
import './LoginContainer.css';
import DynamicForm from './DynamicForm';

const fieldsForLoginForm = [
    { type: 'email', name: 'email', placeholder: 'Email' },
    { type: 'password', name: 'password', placeholder: 'Password' },
];
const LoginContainer = () => {
    return (
            <div className="login-container">
                <p className="login-text">Log in to HabitCare</p>

                <DynamicForm fields={fieldsForLoginForm} buttonText="Login" />
            </div>
    );
}

export default LoginContainer;