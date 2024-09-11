import React, { useState } from 'react';
import { PageHeader, HistoryList, Pagination } from '../components';
import axios from 'axios';

const HistoryRoute = () => {
  const [email, setEmail] = useState();
  const [histories, setHistories] = useState();
  const [page, setPage] = useState();
  const [isSearched, setIsSearched] = useState(false);

  const getHistories = async pageNumber => {
    try {
      const response = await axios.get(
        `http://localhost:8080/order/listEmail?email=${email}&page=${pageNumber}`,
      );
      setIsSearched(true);
      setHistories(response.data.content);
      setPage({
        current: response.data.pageable.pageNumber + 1,
        totalPages: response.data.totalPages,
      });
    } catch (error) {
      console.log(error);
    }
  };

  const handleEmailChanged = e => {
    setEmail(e.target.value);
  };
  const handlePageChanged = e => {
    getHistories(e.selected);
  };

  return (
    <div className="container-fluid">
      <PageHeader />
      <div className="card">
        <div className="row">
          <div className="order-email-search mb-3">
            <label htmlFor="email" className="form-label">
              Email
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
              onClick={() => getHistories(0)}
            >
              검색
            </button>
          </div>
          {isSearched ? (
            <div className="card-container mt-4 d-flex flex-column align-items-start p-3 pt-0">
              <HistoryList
                histories={histories}
                handleCancleSuccess={() => getHistories(page.current - 1)}
              />
              <div className="pagination-container">
                <Pagination
                  pageCount={page.totalPages}
                  onPageChange={handlePageChanged}
                />
              </div>
            </div>
          ) : (
            <div className="text-center align-self-center py-5">
              조회할 이메일을 검색해주세요.
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default HistoryRoute;
