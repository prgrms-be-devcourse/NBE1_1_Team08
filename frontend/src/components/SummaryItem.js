import React from 'react';

const SummaryItem = ({ name, count }) => {
  return (
    <>
      <div className="row">
        <h6 className="p-0">
          {name} <span className="badge bg-dark text-">{count}개</span>
        </h6>
      </div>
    </>
  );
};

export default SummaryItem;
