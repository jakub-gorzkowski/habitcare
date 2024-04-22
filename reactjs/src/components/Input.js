import React from 'react';
import './Input.css';

const Input = ({ type, name, placeholder }) => {
    return (
        <input className="input" type={type} name={name} placeholder={placeholder} />
    );
}

export default Input;