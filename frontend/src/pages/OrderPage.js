import React, { useState, useEffect } from 'react';
import { ProductList, PageHeader, Summary } from '../components';
import axios from 'axios';

const OrderPage = () => {
  const [products, setProducts] = useState([]);
  const [items, setItems] = useState([]);
  const [totalPages, setTotalPages] = useState(0);

  const getProducts = async page => {
    try {
      const response = await axios.get(
        `http://localhost:8080/product/list?page=${page}`,
      );
      setProducts(response.data.content);
      setTotalPages(response.data.totalPages);
    } catch (error) {
      console.log(error);
    }
  };

  const handleAddBtnClick = id => {
    const product = products.find(v => v.productId === id);
    const found = items.find(v => v.productId === id);
    const updatedItems = found
      ? items.map(v =>
          v.productId === id ? { ...v, quantity: v.quantity + 1 } : v,
        )
      : [...items, { ...product, quantity: 1 }];
    setItems(updatedItems);
  };
  const handleMinusItem = id => {
    const found = items.find(v => v.productId === id);
    const updatedItems = found
      ? items
          .map(v =>
            v.productId === id ? { ...v, quantity: v.quantity - 1 } : v,
          )
          .filter(v => v.quantity > 0)
      : items;
    setItems(updatedItems);
  };
  const handlePageChanged = e => {
    getProducts(e.selected);
  };

  useEffect(() => {
    getProducts(0);
  }, []);

  return (
    <div className="container-fluid">
      <PageHeader />
      <div className="card">
        <div className="row">
          <div className="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
            <ProductList
              products={products}
              onAddClick={handleAddBtnClick}
              totalPages={totalPages}
              handlePageChanged={handlePageChanged}
            />
          </div>
          <div className="col-md-4 summary p-4">
            <Summary items={items} handleMinusItem={handleMinusItem} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default OrderPage;
