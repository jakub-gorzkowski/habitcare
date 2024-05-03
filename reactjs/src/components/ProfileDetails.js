import React from 'react';
import './ProfileDetails.css';
import { Link } from 'react-router-dom';
import friendsIcon from '../assets/friends.svg';
import logoutIcon from '../assets/logout.svg';

function ProfileDetails() {
    return (
        <div className="friend-link">
            <Link to="/friends" className="link">
                <img src={friendsIcon} alt="Friends" className="friend-icon" />
                <span className="link-text">Friends</span>
            </Link>
            <Link to="/logout" className="link">
                <img src={logoutIcon} alt="Logout" className="logout-icon" />
                <span className="link-text">Logout</span>
            </Link>
        </div>
    );
}

export default ProfileDetails;
