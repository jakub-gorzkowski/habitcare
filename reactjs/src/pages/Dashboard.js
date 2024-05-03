import React from 'react';
import './Dashboard.css';
import SideBar from '../components/SideBar.js';
import Navbar from "../components/Navbar";
import Button from "../components/Button.js";
import HabitItem from "../components/HabitItem.js";
import MobileMenu from "../components/MobileMenu.js";
function Dashboard() {
    const activePage = "Habits";
  return (
    <div className="dashboard">
        <Navbar />
         <SideBar />
        <div className="habits-container">
            <div className="habits-text">My habits</div>
            <HabitItem label="Going to gym" score="7/10" inviteText="Invite friend"/>
            <HabitItem label="Going to gym" score="7/10" inviteText="Invite friend"/>
            <HabitItem label="Going to gym" score="7/10" inviteText="Invite friend"/>
            <div className="add-habit-btn">
                <Button color="blue">Add Habit</Button>
            </div>
        </div>
        <div className="mobile-menu">
            <MobileMenu activePage={activePage} />
        </div>
    </div>
  );
}

export default Dashboard;