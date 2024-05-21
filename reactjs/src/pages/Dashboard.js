import React from "react";
import "./Dashboard.css";
import SideBar from "../components/SideBar.js";
import Navbar from "../components/Navbar";
import Button from "../components/Button.js";
import HabitItem from "../components/HabitItem.js";
import MobileMenu from "../components/MobileMenu.js";

// import authHeader from "../service/auth-header";
//     axios
//       .get("http://localhost:8080/", {
//         headers: authHeader(), <- zeby dzialalo zapytanie do backednu trzeba dodawac to

function Dashboard() {
  const activePage = "Habits";
  return (
    <div className="dashboard">
      <Navbar />
      <div className="dashboard-middle">
        <div className="side-bar">
          <SideBar />
        </div>
        <div className="habits-container">
          <div className="habits-text">My habits</div>
          <HabitItem
            label="Going to gym"
            score="7/10"
            inviteText="Invite friend"
          />
          <HabitItem
            label="Going to gym"
            score="7/10"
            inviteText="Invite friend"
          />
          <HabitItem
            label="Going to gym"
            score="7/10"
            inviteText="Invite friend"
          />
          <HabitItem
            label="Going to gym"
            score="7/10"
            inviteText="Invite friend"
          />
          <div className="add-habit-input">
            <input type="text" placeholder="Insert habit name" />
          </div>
          <div className="add-habit-btn">
            <Button color="blue">Add Habit</Button>
          </div>
        </div>
      </div>
      <div className="mobile-menu">
        <MobileMenu activePage={activePage} />
      </div>
    </div>
  );
}

export default Dashboard;
