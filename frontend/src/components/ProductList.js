import React from 'react';
import Product from './Product';
import Pagination from './Pagination';

const ProductList = ({
  products = [],
  onAddClick,
  totalPages,
  handlePageChanged,
}) => {
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
        <div className="pagination-container">
          <Pagination pageCount={totalPages} onPageChange={handlePageChanged} />
        </div>
      </div>
    </>
  );
};

export default ProductList;
