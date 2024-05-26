import React, {useEffect, useState} from "react";
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

    useEffect(() => {
        axios.get("http://localhost:8080/api/users/habits", {
            headers: authHeader()
        })
            .then(response => {
                setHabits(response.data);
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    }, []);

    const handleAddHabit = () => {
        axios.post("http://localhost:8080/api/habits/add", {
            name: newHabitName,
            description: ""
        }, {
            headers: authHeader()
        })
            .then(response => {
                setHabits([...habits, {name: newHabitName, score: 0}]);
                setNewHabitName('');
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    }

    return (
        <div className="dashboard">
            <Navbar />
            <div className="dashboard-middle">
                <div className="side-bar">
                    <SideBar />
                </div>
                <div className="habits-container">
                    <div className="habits-text">My habits</div>
                    {habits.map((habit, index) => (
                        <HabitItem
                            key={index}
                            label={habit.name}
                            inviteText="Invite friend"
                        />))}

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