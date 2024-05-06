import React from "react";
import "./HabitItem.css";

function HabitItem({ label, score, inviteText }) {
    return (
        <div className="habit-item">
            <h3 className="label">{label}</h3>
            <div className="score">
                Score: <span className="score-value">{score}</span>
            </div>
            <div className="invite-text">{inviteText}</div>
        </div>
    );
}

export default HabitItem;