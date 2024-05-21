import React from 'react';
import './Navbar.css';
import './RegisterContainer.css';
import registerIcon from '../assets/registerIcon.svg';
import DynamicForm from './DynamicForm';

const fieldsForRegisterForm = [
    { type: 'email', name: 'email', placeholder: 'Email' },
    { type: 'password', name: 'password', placeholder: 'Password' },
    { type: 'text', name: 'username', placeholder: 'Username' },
];
const RegisterContainer = () => {
    return (
            <div className="register-container">
                <p className="register-text">Sign up to HabitCare</p>
                <div className="icon-container">
                    <img loading="lazy" src={registerIcon} className="registerIcon" alt="" />
                </div>

                <DynamicForm fields={fieldsForRegisterForm} buttonText="Sign up" action="http://localhost:8080/api/auth/register" />
            </div>
    );
}

export default RegisterContainer;