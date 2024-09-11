import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import { OrderPage, MainPage, HistoryPage } from './pages';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/order" element={<OrderPage />} />
        <Route path="/order-history" element={<HistoryPage />} />
      </Routes>
    </Router>
  );
}

export default App;
