import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Profile from "./pages/Profile.js";
import Dashboard from "./pages/Dashboard.js";
import Register from "./pages/Register.js";
import Calendar from "./pages/Calendar.js";
import Journal from "./pages/Journal.js";
import Login from "./pages/Login.js";
import Friends from "./pages/Friends";
import HabitDetails from "./pages/HabitDetails";

import LoggedRoutes from "./utils/LoggedRoutes";
import PrivateRoutes from "./utils/PrivateRoutes";

const App = () => {
    // Zalogowany uzytkownik nie moze dostac sie do login/register,
    // a nie zalogowany do dashboard itp
  return (
    <Routes>
      <Route element={<PrivateRoutes />}>
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/calendar" element={<Calendar />} />
        <Route path="/journal" element={<Journal />} />
        <Route path="/friends" element={<Friends />} />
        <Route path="/habitdetails/:habitId" element={<HabitDetails />} />
      </Route>
      <Route element={<LoggedRoutes />}>
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
      </Route>
    </Routes>
  );
};
export default App;
