import React, { useState } from 'react';
import MoodOption from './MoodOption.js';
import Button from "./Button.js";
import './MoodForm.css';

import JoyColor from '../assets/JoyColor.svg';
import SadnessColor from '../assets/SadnessColor.svg';
import FearColor from '../assets/FearColor.svg';
import DisgustColor from '../assets/DisgustColor.svg';
import AngerColor from '../assets/AngerColor.svg';

import Joy from '../assets/Joy.svg';
import Sadness from '../assets/Sadness.svg';
import Fear from '../assets/Fear.svg';
import Disgust from '../assets/Disgust.svg';
import Anger from '../assets/Anger.svg';


const MoodForm = () => {
    const moods = [
        { label: 'Joy', image: Joy, colorImage: JoyColor, color: '#D7C73D' },
        { label: 'Sadness', image: Sadness, colorImage: SadnessColor, color: '#2583D0' },
        { label: 'Fear', image: Fear, colorImage: FearColor, color: '#882A9B' },
        { label: 'Disgust', image: Disgust, colorImage: DisgustColor, color: '#48974E' },
        { label: 'Anger', image: Anger, colorImage: AngerColor, color: '#CE4139' },
    ];
    const [selectedMood, setSelectedMood] = useState(null);

    const handleMoodClick = (mood) => {
        setSelectedMood(mood);
    };
    return (

        <form>
<div className="mood-options">
    {moods.map((moodOption) => (
        <MoodOption
            key={moodOption.label}
            {...moodOption}
            onClick={() => handleMoodClick(moodOption.label)}
            isSelected={selectedMood === moodOption.label}
        />
    ))}
</div>

            <textarea placeholder="Why do you feel this way?" />
            <Button color="blue">Submit</Button>
        </form>
    );
};

export default MoodForm;