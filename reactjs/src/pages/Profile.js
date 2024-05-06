import React from 'react';
import './Profile.css';
import SideBar from '../components/SideBar.js';
import Navbar from "../components/Navbar";
import MobileMenu from "../components/MobileMenu.js";
import ProfileLogo from "../components/ProfileLogo.js";
import ProfileDetails from "../components/ProfileDetails.js";
import myProfile from "../assets/myProfile.svg";
function Profile() {
    const activePage = "Profile";
    return (
        <div className="profile">
            <Navbar />
            <SideBar />
            <ProfileLogo />
            <div className="profile-container">
                <div className="profile-text">My Profile</div>
                <div className="profile-picture">
                    <img src={myProfile} alt="Profile Image"/>
                </div>
                <div className="username">Name</div>
                <div className="profile-details">
                    <ProfileDetails />
                </div>
            </div>
            <div className="mobile-menu">
            <MobileMenu activePage={activePage} />
            </div>
        </div>
    );
}

export default Profile;