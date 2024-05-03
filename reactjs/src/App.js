import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
//import login from './pages/login';
import Dashboard from './pages/Dashboard.js';
import Register from './pages/Register.js';
import Profile from './pages/Profile.js';
const App = () => {
  return (
      <Router>
        <Routes>
          {/*<Route path="/login" element={<Login />} />*/}
            <Route path="/register" element={<Register />} />
          <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/profile" element={<Profile />} />
        </Routes>
      </Router>
  );
};

export default App;