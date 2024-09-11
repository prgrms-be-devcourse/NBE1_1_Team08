import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import { OrderPage, MainPage, HistoryPage, OrderUpdatePage } from './pages';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/order" element={<OrderPage />} />
        <Route path="/order-history" element={<HistoryPage />} />
        <Route path="/order-update" element={<OrderUpdatePage />} />
      </Routes>
    </Router>
  );
}

export default App;
