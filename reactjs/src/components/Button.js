import React from 'react';
import './Button.css';

const Button = ({ color, children, onClick }) => {
    const buttonClassName = `button ${color}`;

    return (
        <button className={buttonClassName} onClick={onClick}>
            {children}
        </button>
    );
}

export default Button;