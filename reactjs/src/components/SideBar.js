import * as React from "react";
import "./SideBar.css";
import progressIcon from "../assets/progress.svg";
import friendsIcon from "../assets/friends.svg";
import calendarIcon from "../assets/calendar.svg";
import habitIcon from "../assets/habit.svg";
import moodJournalIcon from "../assets/moodJournal.svg";
import streaksIcon from "../assets/streaks.svg";
import myProfileIcon from "../assets/myProfile.svg";
import logoutIcon from "../assets/logout.svg";

function MenuItem({ icon, label }) {
  return (
    <div className="menu-item">
      <img loading="lazy" src={icon} className="menu-icon" alt="" />
      <div className="menu-label">{label}</div>
    </div>
  );
}

const menuItems = [
    { icon: myProfileIcon, label: "Profile" },
    { icon: habitIcon, label: "Habits" },
    { icon: friendsIcon, label: "Friends" },
    { icon: streaksIcon, label: "Streaks" },
    { icon: moodJournalIcon, label: "Mood journal" },
    { icon: progressIcon, label: "Progress" },
    { icon: calendarIcon, label: "Calendar" },
];

function SideBar() {
  return (
    <div className="sidebar">
      <div className="menu-title">Menu</div>
      <div className="menu">
        {menuItems.map((item, index) => (
          <MenuItem key={index} icon={item.icon} label={item.label} />
        ))}
      </div>
      <div className="logout">
        <img
          loading="lazy"
          src={logoutIcon}
          className="logout-icon"
          alt=""
        />
        <div className="logout-label">Logout</div>
      </div>
    </div>
  );
}

export default SideBar;