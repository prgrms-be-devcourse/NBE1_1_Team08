import React from 'react';
import { Product } from './Product';

export function ProductList({ products = [], onAddClick }) {
  return (
    <React.Component>
      <h5 className="flex-grow-0">
        <b>상품 목록</b>
      </h5>
      <ul className="list-group products">
        {products.map(v => (
          <Product {...v} key={v.id} onAddClick={onAddClick} />
        ))}
      </ul>
    </React.Component>
  );
}
