import React, { useState, useEffect } from "react";
import "./Calendar.css";
import Navbar from "../components/Navbar";
import SideBar from "../components/SideBar";
import MobileMenu from "../components/MobileMenu";
import axios from 'axios';
import authHeader from '../service/auth-header.js';

import JoyColor from '../assets/JoyColor.svg';
import SadnessColor from '../assets/SadnessColor.svg';
import FearColor from '../assets/FearColor.svg';
import DisgustColor from '../assets/DisgustColor.svg';
import AngerColor from '../assets/AngerColor.svg';

const activePage = "Calendar";

const months = [
    "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
];

const daysOfWeek = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

const moods = [
    { label: 'Joy', image: JoyColor, color: '#D7C73D', id: 1 },
    { label: 'Sadness', image: SadnessColor, color: '#2583D0', id: 2 },
    { label: 'Fear', image: FearColor, color: '#882A9B', id: 3 },
    { label: 'Disgust', image: DisgustColor, color: '#48974E', id: 4 },
    { label: 'Anger', image: AngerColor, color: '#CE4139', id: 5 },
];

const Calendar = () => {
    const [selectedDate, setSelectedDate] = useState(new Date());
    const [habitsDone, setHabitsDone] = useState("");
    const [moodId, setMoodId] = useState(null);
    const [moodDescription, setMoodDescription] = useState("");
    const fetchMoodForDate = async (date) => {
        try {
            const formattedDate = date.toLocaleDateString('en-CA');
            const response = await axios.get(`http://localhost:8080/api/posts/get-by-date/${formattedDate}`, {
                headers: authHeader()
            });
            const moodData = response.data;
            setMoodId(moodData.moodId || null);
            setMoodDescription(moodData.description || "");
        } catch (error) {
            console.error('Error fetching mood for date:', error);
            setMoodId(null);
            setMoodDescription("");
        }
    };
    const fetchHabitsDoneForDate = async (date) => {
        try {
            const formattedDate = date.toLocaleDateString('en-CA');
            const response = await axios.get(`http://localhost:8080/api/habits/daily-checks/${formattedDate}`, {
                headers: authHeader()
            });
            const habitsCount = response.data;
            setHabitsDone(habitsCount);
        } catch (error) {
            console.error('Error fetching habits done for date:', error);
            setHabitsDone("");
        }
    };
    const onChange = (date) => {
        setSelectedDate(date);
        fetchHabitsDoneForDate(date);
        fetchMoodForDate(date);
    };

    useEffect(() => {
        onChange(selectedDate);
    }, [selectedDate]);

    const onPrevMonthClick = () => {
        setSelectedDate((prevDate) => {
            return new Date(prevDate.getFullYear(), prevDate.getMonth() - 1, 1);
        });
    };

    const onNextMonthClick = () => {
        setSelectedDate((prevDate) => {
            return new Date(prevDate.getFullYear(), prevDate.getMonth() + 1, 1);
        });
    };

    const generateCalendarDays = () => {
        const days = [];
        const firstDayOfMonth = new Date(selectedDate.getFullYear(), selectedDate.getMonth(), 1);
        const lastDayOfMonth = new Date(selectedDate.getFullYear(), selectedDate.getMonth() + 1, 0);
        const startingDay = firstDayOfMonth.getDay();

        for (let i = 0; i < startingDay; i++) {
            days.push(<div key={`empty-${i}`} className="calendar-day empty"></div>);
        }

        for (let i = 1; i <= lastDayOfMonth.getDate(); i++) {
            const currentDate = new Date(selectedDate.getFullYear(), selectedDate.getMonth(), i);
            days.push(
                <div
                    key={i}
                    className={`calendar-day ${currentDate.toDateString() === selectedDate.toDateString() ? 'selected' : ''}`}
                    onClick={() => onChange(currentDate)}
                >
                    {i}
                </div>
            );
        }

        return days;
    };
    const getMoodImage = () => {
        const mood = moods.find(m => m.id === moodId);
        return mood ? mood.image : null;
    };
    return (
        <div className="calendar">
            <Navbar/>
            <SideBar/>
            <div className="calendar-container-1">
                <div className="calendar-container-2">
                    <div className="calendar-container-3">
                        <div className="month-picker">
                            <button onClick={onPrevMonthClick}>{'<'}</button>
                            <h3>{months[selectedDate.getMonth()]} {selectedDate.getFullYear()}</h3>
                            <button onClick={onNextMonthClick}>{'>'}</button>
                        </div>
                        <div className="calendar-grid">
                            {daysOfWeek.map((day) => (
                                <div key={day} className="calendar-header">
                                    {day}
                                </div>
                            ))}
                            {generateCalendarDays()}
                        </div>
                    </div>
                </div>
                <div className="selected-date-info">
                    {selectedDate && (
                        <div className="emoji-habit-contanier">
                            <h3>{selectedDate.toDateString()}</h3>
                            <div className="mood-habits-container">
                                <div className="mood-emoji">
                                    {moodId && <img src={getMoodImage()} className="emotion-img" alt="Mood Emoji"/>}
                                </div>
                                <div className="habits-done">
                                    <span>Habits done:</span> {habitsDone}
                                </div>
                            </div>
                            <div className="mood-description-container">
                                {moodDescription}
                            </div>
                        </div>
                    )}
                </div>
            </div>
            <div className="mobile-menu">
                <MobileMenu activePage={activePage}/>
            </div>
        </div>
    );
};

export default Calendar;
