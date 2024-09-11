import React from 'react';
import Product from './Product';

const ProductList = ({ products = [], onAddClick }) => {
  return (
    <>
      <h5 className="flex-grow-0">
        <b>상품 목록</b>
      </h5>
      <ul className="list-group products">
        {products.map(v => (
          <Product {...v} key={v.id} onAddClick={onAddClick} />
        ))}
      </ul>
    </>
  );
};

export default ProductList;
