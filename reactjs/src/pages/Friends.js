import React from 'react';
import './Friends.css';
import SideBar from '../components/SideBar.js';
import Navbar from "../components/Navbar";
import MobileMenu from "../components/MobileMenu.js";
import Friend from "../components/Friend";
import myProfile from "../assets/myProfile.svg";

function Friends() {
    const activePage = "Friends";
    return (
        <div className="friends">
            <Navbar />
            <div className="friends-middle">
                <div className="side-bar">
                    <SideBar/>
                </div>
                <div className="friends-container">
                    <div className="friends-text">Friends</div>
                    <Friend friendName="Name Surname" friendPicture={myProfile}/>
                    <Friend friendName="Name Surname" friendPicture={myProfile}/>
                    <Friend friendName="Name Surname" friendPicture={myProfile}/>
                    <Friend friendName="Name Surname" friendPicture={myProfile}/>

                </div>
            </div>
            <div className="mobile-menu">
                <MobileMenu activePage={activePage} />
            </div>
        </div>
    );
}

export default Friends;