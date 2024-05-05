import React from 'react';
import './Journal.css';
import SideBar from '../components/SideBar.js';
import Navbar from "../components/Navbar";
import MobileMenu from "../components/MobileMenu.js";
import MoodForm from '../components/MoodForm.js';
function Journal() {
    const activePage = "Habits";
    return (
        <div className="dashboard">
            <Navbar />
            <div className="journal-middle">
                <div className="side-bar">
                    <SideBar/>
                </div>
                <div className="habits-container">
                    <div className="habits-text">Mood journal</div>
                    <p className={"journal-text"}>What are you feeling today?</p>
                    <MoodForm />

                </div>
            </div>
            <div className="mobile-menu">
                <MobileMenu activePage={activePage} />
            </div>
        </div>
    );
}

export default Journal;