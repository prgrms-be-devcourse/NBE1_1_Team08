import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { PageHeader } from '../components';

const MainPage = () => {
  const [topProducts, setTopProducts] = useState();

  useEffect(() => {
    setTopProducts();
  });

  return (
    <div className="container-fluid">
      <PageHeader />
      <div className="card">
        <Link to="/order">
          <button className="btn btn-primary">상품 주문</button>
        </Link>
        <Link to="/order-history">
          <button className="btn btn-primary">주문 내역</button>
        </Link>
      </div>
    </div>
  );
};

export default MainPage;
