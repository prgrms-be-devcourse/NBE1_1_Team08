import React, { useState, useEffect } from 'react';
import { ProductList, PageHeader, Summary } from '../components';
import axios from 'axios';

const OrderPage = () => {
  const [products, setProducts] = useState([]);
  const [items, setItems] = useState([]);

  const handleAddBtnClick = id => {
    const product = products.find(v => v.productId === id);
    const found = items.find(v => v.productId === id);
    const updatedItems = found
      ? items.map(v => (v.productId === id ? { ...v, count: v.count + 1 } : v))
      : [...items, { ...product, count: 1 }];
    setItems(updatedItems);
  };

  useEffect(() => {
    try {
      axios
        .get('http://localhost:8080/product/list')
        .then(v => setProducts(v.data.content));
    } catch (error) {
      console.log(error);
    }
  }, []);

  return (
    <div className="container-fluid">
      <PageHeader />
      <div className="card">
        <div className="row">
          <div className="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
            <ProductList products={products} onAddClick={handleAddBtnClick} />
          </div>
          <div className="col-md-4 summary p-4">
            <Summary items={items} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default OrderPage;
