import React from 'react';
import './Navbar.css';
import logo from '../assets/logo.svg';

const Navbar = () => {
    return (
        <nav className='navbar-container'>
            <img className='logo' src={logo} alt="Logo" />
            <div className="navbar-links">
                <ul>
                    <a href="aboutUs">About Us</a>
                    <a href="contacts">Contacts</a>
                </ul>
            </div>
        </nav>
    );
}

export default Navbar;