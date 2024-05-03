
import React from "react";
import "./ProfileLogo.css";

function ProfileLogo() {
    return (
        <div className="profile-logo">
            <div className="pl">
                <img
                    loading="lazy"
                    src="https://cdn.builder.io/api/v1/image/assets/TEMP/dd5829995982adea4b1f65c412c643553d68a1308b50aaea33023356eb8170d3?apiKey=cd2e064595044c2c9413206bd79a5e35&"
                    alt="HabitCare logo"
                    className="logo"
                />
                <h1 className="title">HabitCare</h1>
            </div>
        </div>
    );
}

export default ProfileLogo;