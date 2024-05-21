import React from 'react';
import './HabitDetails.css';
import SideBar from '../components/SideBar.js';
import Navbar from "../components/Navbar";
import MobileMenu from "../components/MobileMenu.js";
import CircularChart from "../components/CircuralChart";
import bestStreaks from "../assets/bestStreaks.svg"
import habitsDone from "../assets/habitsDone.svg"
import Button from "../components/Button";

function HabitDetails() {
    const activePage = "Habits";
    return (
        <div className="habitdetails">
            <Navbar />
            <div className="habitdetails-middle">
                <div className="side-bar">
                    <SideBar/>
                </div>
                <div className="habitdetails-container">
                    <div className="habitName"> Habit Name</div>
                    <div className="circural">
                        <CircularChart percentage={80}/>
                    </div>
                    <div className="details">
                        <div className="details-container">
                            <img loading="lazy" src={bestStreaks} alt="Best Streaks"/>
                            <div className="streak-text">Best Streaks</div>
                            <div> 7</div>
                        </div>
                        <div className="details-container">
                            <img loading="lazy" src={habitsDone} alt="Habits Done"/>
                            <div className="streak-text">Habits Done</div>
                            <div> 7</div>
                        </div>
                    </div>
                    <Button color="blue">Delete</Button>
                </div>
            </div>
            <div className="mobile-menu">
                <MobileMenu activePage={activePage}/>
            </div>
        </div>
    );
}

export default HabitDetails;