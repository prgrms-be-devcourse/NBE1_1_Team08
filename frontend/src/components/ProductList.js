import React from 'react';
import Product from './Product';

const ProductList = ({ products = [], onAddClick }) => {
  return (
    <>
      <h5 className="flex-grow-0">
        <b>Products</b>
      </h5>
      <div className="product-container">
        <ul className="list-group products">
          {products.map(v => (
            <Product {...v} key={v.id} onAddClick={onAddClick} />
          ))}
        </ul>
      </div>
    </>
  );
};

export default ProductList;
