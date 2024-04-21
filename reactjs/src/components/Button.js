import React from 'react';
import './Button.css';

const Button= ({ color, children }) => {
    const buttonClassName = `button ${color}`;

    return (
        <button className={buttonClassName}>
            {children}
        </button>
    );
}

export default Button;