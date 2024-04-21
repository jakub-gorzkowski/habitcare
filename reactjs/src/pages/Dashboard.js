import React from 'react';
import './Dashboard.css';
import SideBar from '../components/SideBar.js';
import Navbar from "../components/Navbar";
import Button from "../components/Button.js";
function Dashboard() {
  return (
    <div className="desktop">
        <Navbar />
         <SideBar />
        <div className="habits-container">
            <Button color="blue">Add Habit</Button>
        </div>
       </div>
  );
}

export default Dashboard;