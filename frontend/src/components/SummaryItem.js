import React from 'react';
import { FiMinus } from 'react-icons/fi';

const SummaryItem = ({ id, name, count, onMinusItemClicked }) => {
  return (
    <>
      <div className="row">
        <h6 className="p-0 px-3">
          {name}{' '}
          <span className="badge bg-info text-bg-secondary text-">{count}</span>
          <span
            className="badge bg-secondary text-bg-secondary ms-1"
            onClick={() => onMinusItemClicked(id)}
          >
            <FiMinus />
          </span>
        </h6>
      </div>
    </>
  );
};

export default SummaryItem;
