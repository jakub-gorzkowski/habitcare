import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
//import login from './pages/login;
import Desktop from './pages/Desktop.js';
const App = () => {
  return (
      <Router>
        <Routes>
          {/*<Route path="/login" element={<Login />} />*/}
          <Route path="/desktop" element={<Desktop />} />
        </Routes>
      </Router>
  );
};

export default App;