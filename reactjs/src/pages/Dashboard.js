import React, { useEffect, useState } from "react";
import "./Dashboard.css";
import SideBar from "../components/SideBar.js";
import Navbar from "../components/Navbar";
import Button from "../components/Button.js";
import HabitItem from "../components/HabitItem.js";
import MobileMenu from "../components/MobileMenu.js";
import authHeader from "../service/auth-header";
import axios from "axios";

function Dashboard() {
    const activePage = "Habits";
    const [habits, setHabits] = useState([]);
    const [newHabitName, setNewHabitName] = useState('');
    const [scores, setScores] = useState({});

    useEffect(() => {
        axios.get("/api/users/habits", {
            headers: authHeader()
        })
            .then(response => {
                setHabits(response.data);
                const habitIds = response.data.map(habit => habit.id);
                fetchMonthlyScores(habitIds);
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    }, []);

    const handleAddHabit = () => {
        if (!newHabitName.trim()) {
            alert("Habit name cannot be empty.");
            return;
        }

        axios.post("/api/habits/add", {
            name: newHabitName,
            description: ""
        }, {
            headers: authHeader()
        })
            .then(response => {
                setHabits([...habits, { name: newHabitName, id: response.data.id }]);
                setNewHabitName('');
                setScores(prevScores => ({
                    ...prevScores,
                    [response.data.id]: 0
                }));
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    };

    const fetchMonthlyScores = (habitIds) => {
        habitIds.forEach(habitId => {
            axios.get(`/api/habits/monthly-checks/${habitId}`, {
                headers: authHeader()
            })
                .then(response => {
                    setScores(prevScores => ({
                        ...prevScores,
                        [habitId]: response.data
                    }));
                })
                .catch(error => {
                    console.error(`Error fetching monthly score for habit ${habitId}:`, error);
                });
        });
    };

    return (
        <div className="dashboard">
            <Navbar />
            <div className="dashboard-middle">
                <div className="side-bar">
                    <SideBar />
                </div>
                <div className="habits-container">
                    <div className="habits-text">My habits</div>
                    {habits.map(habit => (
                        <HabitItem
                            key={habit.id}
                            label={habit.name}
                            inviteText="Invite friend"
                            habitId={habit.id}
                            score={scores[habit.id] || 0}
                            setScores={setScores}
                        />
                    ))}

                    <div className="add-habit-input">
                        <input type="text" placeholder="Insert habit name" value={newHabitName} onChange={e => setNewHabitName(e.target.value)} />
                    </div>
                    <div className="add-habit-btn">
                        <Button color="blue" onClick={handleAddHabit}>Add Habit</Button>
                    </div>
                </div>
            </div>
            <div className="mobile-menu">
                <MobileMenu activePage={activePage} />
            </div>
        </div>
    );
}

export default Dashboard;
