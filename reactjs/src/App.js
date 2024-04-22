import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
//import login from './pages/login;
import Dashboard from './pages/Dashboard.js';
import Register from './pages/Register.js';
import Login from './pages/Login.js';
const App = () => {
  return (
      <Router>
        <Routes>
          <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </Routes>
      </Router>
  );
};

export default App;