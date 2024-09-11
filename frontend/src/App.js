import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import {
  OrderRoute,
  MainRoute,
  HistoryRoute,
  OrderUpdateRoute,
} from './routes';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MainRoute />} />
        <Route path="/order" element={<OrderRoute />} />
        <Route path="/order-history" element={<HistoryRoute />} />
        <Route path="/order-update" element={<OrderUpdateRoute />} />
      </Routes>
    </Router>
  );
}

export default App;
