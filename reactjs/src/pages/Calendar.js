import React, { useState, useEffect } from "react";
import "./Calendar.css";
import Navbar from "../components/Navbar";
import SideBar from "../components/SideBar";
import joy from "../assets/joy.svg";
import MobileMenu from "../components/MobileMenu";

const activePage = "Calendar";

const months = [
    "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
];

const daysOfWeek = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

const Calendar = () => {
    const [selectedDate, setSelectedDate] = useState(new Date());
    const [habitsDone, setHabitsDone] = useState("");
    const [moodEmoji, setMoodEmoji] = useState("");
    const [moodDescription, setMoodDescription] = useState("");

    const onChange = (date) => {
        setSelectedDate(date);
        setHabitsDone("6/7");
        setMoodDescription("Dziś czuję się smutno, ponieważ rano spóźniłem się do pracy, potem miałem kłótnię z przyjacielem, a na koniec złamałem ulubioną filiżankę do kawy.");
    };

    useEffect(() => {
        onChange(selectedDate); // Wywołanie funkcji onChange przy montowaniu komponentu
    }, []); // Pusta tablica zależności sprawi, że useEffect zostanie wywołany tylko raz, po zamontowaniu komponentu

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
                                    <img src={joy} className="emotion-img" alt="Joy Emoji"/>
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
