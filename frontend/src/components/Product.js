import React from 'react';
import { FiPlus } from 'react-icons/fi';
import { formatPrice } from '../common';

const Product = ({
  productId,
  productName,
  category,
  price,
  image_url,
  onAddClick,
}) => {
  const handleAddBtnClick = () => {
    onAddClick(productId);
  };

  return (
    <>
      <li key={productId} className="list-group-item d-flex mt-3">
        <div className="col-2">
          <img className="img-fluid" src={image_url} alt="" />
        </div>
        <div className="col-6">
          <div className="row text-muted">{category}</div>
          <div className="row">{productName}</div>
        </div>
        <div className="col-2 text-center price">{formatPrice(price)}</div>
        <div className="col-2 text-end action">
          <button
            onClick={handleAddBtnClick}
            className="btn btn-sm btn-outline-dark"
          >
            <FiPlus />
          </button>
        </div>
      </li>
    </>
  );
};

export default Product;
