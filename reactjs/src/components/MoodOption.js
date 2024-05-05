import React from 'react';
import './MoodOption.css';

const MoodOption = ({ image, colorImage, label, onClick, isSelected, color }) => (
    <div className={"mood-container"} onClick={onClick}>
        <img src={isSelected ? colorImage : image} alt={label} />
        <p className={"mood-option"} style={{border: isSelected ? `3px solid ${color}` : '3px solid #667080'}}>{label}</p>
    </div>
);

export default MoodOption;