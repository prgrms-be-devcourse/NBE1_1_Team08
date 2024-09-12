import React from 'react';
import History from './History';

const HistoryList = ({ histories = [], handleCancleSuccess }) => {
  return (
    <>
      {histories.length === 0 ? null : (
        <h5 className="flex-grow-0">
          <b>주문 내역</b>
        </h5>
      )}
      <ul className="list-group products">
        {histories.map(v => (
          <History
            {...v}
            key={v.orderId}
            handleCancleSuccess={handleCancleSuccess}
          />
        ))}
      </ul>
    </>
  );
};

export default HistoryList;
