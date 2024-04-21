import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
//import login from './pages/login;
import Dashboard from './pages/Dashboard.js';
const App = () => {
  return (
      <Router>
        <Routes>
          {/*<Route path="/login" element={<Login />} />*/}
          <Route path="/dashboard" element={<Dashboard />} />
        </Routes>
      </Router>
  );
};

export default App;