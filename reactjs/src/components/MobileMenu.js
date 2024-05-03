import React from "react";
import { Link } from "react-router-dom";
import "./MobileMenu.css";
import mobileInvitations from "../assets/mobileInvitations.svg";
import mobileHabit from "../assets/mobileHabits.svg";
import mobileProfile from "../assets/mobileProfil.svg";
import mobileJournal from "../assets/mobileJournal.svg";
import mobileCalendar from "../assets/mobileCalendar.svg";
import habitIcon from "../assets/habit.svg";
import moodJournal from "../assets/moodJournal.svg";
import calendar from "../assets/calendar.svg";
import profile from "../assets/profile.svg";

const navItems = [
    { icon: mobileInvitations, label: "Invitations", to: "/invitations" },
    { icon: mobileHabit, label: "Habits", to: "/dashboard" },
    { icon: mobileProfile, label: "Profile", to: "/profile" },
    { icon: mobileJournal, label: "Journal", to: "/journal" },
    { icon: mobileCalendar, label: "Calendar", to: "/calendar" },
];
const NavItem = ({ icon, label, to, isActive }) => {
    return (
        <Link to={to} className={`nav-item ${isActive ? 'active' : ''}`}>
            <img loading="lazy" src={icon} className="nav-icon" alt="" />
            <div className={`nav-label ${isActive ? 'active-label' : ''}`} style={{ color: isActive ? '#77CEFF' : 'inherit' }}>{label}</div>
        </Link>
    );
};

function MobileMenu({ activePage }) {
    return (
        <nav className="nav-menu">
            {navItems.map((item, index) => (
                <NavItem
                    key={index}
                    icon={item.icon}
                    label={item.label}
                    to={item.to}
                    isActive={activePage === item.label}
                />
            ))}
        </nav>
    );
}

export default MobileMenu;