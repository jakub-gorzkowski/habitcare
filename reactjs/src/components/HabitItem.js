import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./HabitItem.css";
import axios from "axios";
import authHeader from "../service/auth-header";

function HabitItem({ label, inviteText, habitId, score, setScores }) {
    const [isChecked, setIsChecked] = useState(false);

    const handleCheckboxChange = async () => {
        try {
            await axios.post(
                `/api/habits/add-check/${habitId}`,
                null,
                {
                    headers: authHeader(),
                }
            );
            setIsChecked(!isChecked);
            setScores(prevScores => ({
                ...prevScores,
                [habitId]: score + 1 
            }));
            alert("Check added successfully.");
        } catch (error) {
            console.error('There was an error while adding check:', error);
        }
    };

    const getDaysInMonth = () => {
        const date = new Date();
        return new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();
    };

    const scoreText = `${score}/${getDaysInMonth()}`;

    return (
        <div className="habit-item">
            <h3 className="label">{label}</h3>
            <div className="check">
                <input
                    type="checkbox"
                    className="checkbox"
                    checked={isChecked}
                    onChange={handleCheckboxChange}
                />
            </div>
            <div className="score">
                Score: <span className="score-value">{scoreText}</span>
            </div>
            <Link to={`/habitdetails/${habitId}`} className="label-link">
                <div className="invite-text">{inviteText}</div>
            </Link>
        </div>
    );
}

export default HabitItem;
