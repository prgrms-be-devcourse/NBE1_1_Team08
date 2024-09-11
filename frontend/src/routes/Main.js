import React, { useState, useEffect } from 'react';
import { PageHeader } from '../components';
import { formatPrice } from '../common';

const MainRoute = () => {
  const [topProducts, setTopProducts] = useState([]);

  useEffect(() => {
    setTopProducts([
      {
        productId: 'b8506bb7-eb5d-47a8-81a3-4747bb767624',
        img_url:
          'https://image.istarbucks.co.kr/upload/store/skuimg/2023/11/[11059722]_20231110094425022.jpg',
        productName: '디카페인 하우스 블렌드',
        price: 11000,
      },
      {
        productId: 'b8506bb7-eb5d-47a8-81a3-4747bb767624',
        img_url:
          'https://image.istarbucks.co.kr/upload/store/skuimg/2024/08/[11160368]_20240808101951857.jpg',
        productName: '선 드라이드 브라질 파젠다 산타 클라',
        price: 11000,
      },
      {
        productId: 'b8506bb7-eb5d-47a8-81a3-4747bb767624',
        img_url:
          'https://image.istarbucks.co.kr/upload/store/skuimg/2023/11/[11059722]_20231110094425022.jpg',
        productName: '디카페인 하우스 블렌드',
        price: 11000,
      },
    ]);
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
                <img src={v.img_url} />
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
