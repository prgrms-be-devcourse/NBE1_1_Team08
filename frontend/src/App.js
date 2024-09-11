import React, { useState, useEffect } from 'react';
import { ProductList, Summary } from './components/index';

import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import axios from 'axios';

function App() {
  const [products, setProducts] = useState([]);

  const [items, setItems] = useState([]);

  const handleAddBtnClick = id => {
    const product = products.find(v => v.id === id);
    const found = items.find(v => v.id === id);
    const updatedItems = found
      ? items.map(v => (v.id === id ? { ...v, count: v.count + 1 } : v))
      : [...items, { ...product, count: 1 }];
    setItems(updatedItems);
  };
  // const handleOrderSubmit = order => {};

  useEffect(() => {
    axios
      .get('http://localhost:8080/api/v1/products')
      .then(v => setProducts(v.data));
  }, []);

  return (
    <div className="container-fluid">
      <div className="row justify-content-center m-4">
        <h1 className="text-center">Grids & Circle</h1>
      </div>
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
}

export default App;
