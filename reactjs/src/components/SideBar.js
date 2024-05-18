import * as React from "react";
import { Link } from "react-router-dom";
import "./SideBar.css";
import progressIcon from "../assets/progress.svg";
import friendsIcon from "../assets/friends.svg";
import calendarIcon from "../assets/calendar.svg";
import habitIcon from "../assets/habit.svg";
import moodJournalIcon from "../assets/moodJournal.svg";
import streaksIcon from "../assets/streaks.svg";
import myProfileIcon from "../assets/myProfile.svg";
import logoutIcon from "../assets/logout.svg";

function MenuItem({ icon, label, to }) {
    return (
        <Link to={to} className="menu-item">
            <img loading="lazy" src={icon} className="menu-icon" alt="" />
            <div className="menu-label">{label}</div>
        </Link>
    );
}

const menuItems = [
    { icon: myProfileIcon, label: "Profile", to: "/profile" },
    { icon: habitIcon, label: "Habits", to: "/dashboard" },
    { icon: friendsIcon, label: "Friends", to: "/friends" },
    { icon: streaksIcon, label: "Streaks", to: "/streaks" },
    { icon: moodJournalIcon, label: "Mood journal", to: "/journal" },
    { icon: progressIcon, label: "Progress", to: "/progress" },
    { icon: calendarIcon, label: "Calendar", to: "/calendar" },
];

function SideBar() {
    return (
        <div className="sidebar">
            <div className="menu-title">Menu</div>
            <div className="menu">
                {menuItems.map((item, index) => (
                    <MenuItem key={index} icon={item.icon} label={item.label} to={item.to} />
                ))}
            </div>
            <Link to="/logout" className="logout">
                <img loading="lazy" src={logoutIcon} className="logout-icon" alt="" />
                <div className="logout-label">Logout</div>
            </Link>
        </div>
    );
}

export default SideBar;