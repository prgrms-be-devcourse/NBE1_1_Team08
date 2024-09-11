import React, { useState, useEffect } from 'react';
import { PageHeader, HistoryList } from '../components';
import axios from 'axios';

const HistoryPage = () => {
  const [email, setEmail] = useState();
  const [histories, setHistories] = useState();

  const getHistories = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/order/listEmail?email=${email}`,
      );
      setHistories(response.data.content);
    } catch (error) {
      console.log(error);
    }
  };

  const handleEmailChanged = e => {
    setEmail(e.target.value);
  };
  const handleCancleSuccess = () => {
    getHistories();
  };
  const handleSearchClicked = () => {
    getHistories();
  };

  return (
    <div className="container-fluid">
      <PageHeader />
      <div className="card">
        <div className="row">
          <div className="order-email-search mb-3">
            <label htmlFor="email" className="form-label">
              주문 이메일
            </label>
            <input
              type="email"
              className="form-control mb-1 email"
              id="email"
              value={email}
              onChange={handleEmailChanged}
            />
            <button
              className="btn btn-sm btn-outline-dark me-2"
              onClick={handleSearchClicked}
            >
              검색
            </button>
          </div>
          <div className="mt-4 d-flex flex-column align-items-start p-3 pt-0">
            <HistoryList
              histories={histories}
              handleCancleSuccess={handleCancleSuccess}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default HistoryPage;
