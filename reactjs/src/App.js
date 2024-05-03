import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Profile from './pages/Profile.js';
import Dashboard from './pages/Dashboard.js';
import Register from './pages/Register.js';
import Calendar from "./pages/Calendar.js"

const App = () => {
    return (
        <Router>
            <Routes>
                {/*<Route path="/login" element={<Login />} />*/}
                <Route path="/register" element={<Register />} />
                <Route path="/dashboard" element={<Dashboard />} />
                <Route path="/profile" element={<Profile />} />
                <Route path="/calendar" element={<Calendar />} />
            </Routes>
        </Router>
    );
};
export default App;