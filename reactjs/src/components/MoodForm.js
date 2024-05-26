import React, { useState } from 'react';
import MoodOption from './MoodOption.js';
import Button from "./Button.js";
import './MoodForm.css';
import axios from 'axios';
import authHeader from '../service/auth-header.js';
import JoyColor from '../assets/JoyColor.svg';
import SadnessColor from '../assets/SadnessColor.svg';
import FearColor from '../assets/FearColor.svg';
import DisgustColor from '../assets/DisgustColor.svg';
import AngerColor from '../assets/AngerColor.svg';

import Joy from '../assets/joy.svg';
import Sadness from '../assets/Sadness.svg';
import Fear from '../assets/Fear.svg';
import Disgust from '../assets/Disgust.svg';
import Anger from '../assets/Anger.svg';


const MoodForm = () => {
    const moods = [
        { label: 'Joy', image: Joy, colorImage: JoyColor, color: '#D7C73D' , id: 1 },
        { label: 'Sadness', image: Sadness, colorImage: SadnessColor, color: '#2583D0' , id: 2 },
        { label: 'Fear', image: Fear, colorImage: FearColor, color: '#882A9B', id: 3  },
        { label: 'Disgust', image: Disgust, colorImage: DisgustColor, color: '#48974E' , id: 4 },
        { label: 'Anger', image: Anger, colorImage: AngerColor, color: '#CE4139' , id: 5 },
    ];
    const [selectedMood, setSelectedMood] = useState(null);
    const [description, setDescription] = useState('');
    const handleMoodClick = (moodId) => {
        setSelectedMood(moodId);
        console.log(moodId);
    };
    const handleDescriptionChange = (event) => {
        setDescription(event.target.value);
    };
    const handleFormSubmit = (event) => {
        event.preventDefault();

        if (!selectedMood || !description) {
            console.error('Please select a mood and provide a description.');
            return;
        }

        axios.post('http://localhost:8080/api/posts/add', {
            moodId: selectedMood,
            description: description
        }, { headers: authHeader() })
            .then(response => {
                console.log('Post added successfully:', response.data);
                alert('Post added successfully');
                // Optionally, reset form fields or show a success message
            })
            .catch(error => {
                console.error('Error adding post:', error);
                if (error.response && error.response.status === 405) {
                    alert('You can only add one mood per day');
                } else {
                    alert('An error occurred while adding the post');
                }
            });
    };


    return (

        <form onSubmit={handleFormSubmit}>
<div className="mood-options">
    {moods.map((moodOption) => (
        <MoodOption
            key={moodOption.label}
            {...moodOption}
            onClick={() => handleMoodClick(moodOption.id)}
            isSelected={selectedMood === moodOption.id}
        />
    ))}
</div>

            <textarea placeholder="Why do you feel this way?" value={description} onChange={handleDescriptionChange}/>
            <Button color="blue" type="submit">Submit</Button>
        </form>
    );
};

export default MoodForm;