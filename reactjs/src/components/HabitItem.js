import React, { useState } from "react";
import "./HabitItem.css";

function HabitItem({ label, score, inviteText }) {
    const [isChecked, setIsChecked] = useState(false);

    const handleCheckboxChange = () => {
        setIsChecked(!isChecked);
    };
    return (
        <div className="habit-item">
            <h3 className="label">{label}</h3>
            <div className="check"><input
                type="checkbox"
                className="checkbox"
                checked={isChecked}
                onChange={handleCheckboxChange}
            /></div>
            <div className="score">
                Score: <span className="score-value">{score}</span>
            </div>
            <div className="invite-text">{inviteText}</div>
        </div>
    );
}

export default HabitItem;