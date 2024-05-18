import React from "react";
import "./Friend.css";
import accept from "../assets/accept.svg";
import cancel from "../assets/cancel.svg";

function Friend({ friendName, friendPicture }) {
    return (
        <div className="friend">
            <div className="friend-picture">
                <img src={friendPicture} alt="Profile Image"/>
            </div>
            <div className="username">{friendName}</div>
            <div className="invitation-buttons">
                <img loading="lazy" src={accept} alt=""/>
                <img loading="lazy" src={cancel} alt=""/>
            </div>
        </div>
    );
}

export default Friend;