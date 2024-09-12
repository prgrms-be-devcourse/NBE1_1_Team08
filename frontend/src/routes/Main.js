import React, { useState, useEffect } from 'react';
import { PageHeader } from '../components';
import { formatPrice } from '../common';
import axios from 'axios';

const MainRoute = () => {
  const [topProducts, setTopProducts] = useState([]);

  const getPopularProducts = async () => {
    try {
      const response = await axios.get('http://localhost:8080/product/popular');
      setTopProducts(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getPopularProducts();
  }, []);

  return (
    <div className="container-fluid">
      <PageHeader />
      <div className="card">
        <div className="col-md-8 w-auto mt-4 d-flex flex-column align-items-start p-3 pt-0">
          <h5 className="flex-grow-0 align-self-center">
            <b>Today Popular Products</b>
          </h5>
          <div className="main-product-container my-5">
            {topProducts.map(v => (
              <div key={v.productId} className="product-item col">
                <img src={v.image_url} />
                <p className="product-name">
                  <span className="badge bg-success fs-6 w-auto text-break">
                    {v.productName}
                  </span>
                </p>
                <p className="product-price">{formatPrice(v.price)}</p>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default MainRoute;
