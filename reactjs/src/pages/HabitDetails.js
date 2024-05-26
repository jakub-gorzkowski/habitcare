import React, { useEffect, useState } from 'react';
import './HabitDetails.css';
import SideBar from '../components/SideBar.js';
import Navbar from "../components/Navbar";
import MobileMenu from "../components/MobileMenu.js";
import CircularChart from "../components/CircuralChart";
import bestStreaks from "../assets/bestStreaks.svg";
import habitsDone from "../assets/habitsDone.svg";
import Button from "../components/Button";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import authHeader from "../service/auth-header";

function HabitDetails() {
    const { habitId } = useParams();
    const [habit, setHabit] = useState(null);
    const [streak, setStreak] = useState(null);
    const [habitsDoneCount, setHabitsDoneCount] = useState(null);
    const [monthlyCheckPercentage, setMonthlyCheckPercentage] = useState(null);
    const activePage = "Habits";
    const navigate = useNavigate();

    useEffect(() => {
        const fetchHabitDetails = async () => {
            try {
                const response = await axios.get(`/api/habits/get/${habitId}`, {
                    headers: authHeader()
                });
                setHabit(response.data);
            } catch (error) {
                console.error('Error fetching habit details:', error);
            }
        };

        fetchHabitDetails();
    }, [habitId]);

    useEffect(() => {
        const fetchHabitStreaks = async () => {
            try {
                const response = await axios.get(`/api/habits/streak/${habitId}`,  {
                    headers: authHeader()
                });
                setStreak(response.data);
            } catch (error) {
                console.error('Error fetching habit streaks:', error);
            }
        };

        fetchHabitStreaks();
    }, [habitId]);

    useEffect(() => {
        const fetchHabitsDoneCount = async () => {
            try {
                const response = await axios.get(`/api/habits/monthly-checks/${habitId}`, {
                    headers: authHeader()
                });
                setHabitsDoneCount(response.data);
            } catch (error) {
                console.error('Error fetching habits done count:', error);
            }
        };

        fetchHabitsDoneCount();
    }, [habitId]);

    useEffect(() => {
        const fetchMonthlyCheckPercentage = async () => {
            try {
                const response = await axios.get(`/api/habits/monthly-percent/${habitId}`, {
                    headers: authHeader()
                });
                setMonthlyCheckPercentage(response.data);
            } catch (error) {
                console.error('Error fetching monthly check percentage:', error);
            }
        };

        fetchMonthlyCheckPercentage();
    }, [habitId]);

    const handleDelete = async () => {
        try {
            await axios.delete(`/api/habits/delete/${habitId}`, {
                headers: authHeader()
            });
            alert("Habit deleted successfully");
            navigate("/dashboard");
        } catch (error) {
            console.error('Error deleting habit:', error);
        }
    };


    return (
        <div className="habitdetails">
            <Navbar />
            <div className="habitdetails-middle">
                <div className="side-bar">
                    <SideBar />
                </div>
                <div className="habitdetails-container">
                    <div className="habitName">
                        {habit ? habit.name : 'Loading...'}
                    </div>
                    <div className="circural">
                        <CircularChart percentage={monthlyCheckPercentage !== null ? monthlyCheckPercentage : 0} />
                    </div>
                    <div className="details">
                        <div className="details-container">
                            <img loading="lazy" src={bestStreaks} alt="Best Streaks" />
                            <div className="streak-text">Best Streaks</div>
                            <div>{streak !== null ? streak : 'Loading...'}</div>
                        </div>
                        <div className="details-container">
                            <img loading="lazy" src={habitsDone} alt="Habits Done" />
                            <div className="streak-text">Habits Done</div>
                            <div>{habitsDoneCount !== null ? habitsDoneCount : 'Loading...'}</div>
                        </div>
                    </div>
                    <Button color="blue" onClick={handleDelete}>Delete</Button>
                </div>
            </div>
            <div className="mobile-menu">
                <MobileMenu activePage={activePage} />
            </div>
        </div>
    );
}

export default HabitDetails;
