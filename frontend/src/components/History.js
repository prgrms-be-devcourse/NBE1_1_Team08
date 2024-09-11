import React from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

const History = ({
  orderId,
  email,
  address,
  postcode,
  orderStatus,
  createdAt,
  handleCancleSuccess,
}) => {
  const formatDate = dateString => {
    const date = new Date(dateString);
    return date
      .toLocaleString('ko-KR', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: true,
      })
      .replace(',', ' ');
  };

  const cancleOrder = async () => {
    try {
      await axios.get(`http://localhost:8080/order/cancle?id=${orderId}`);
      handleCancleSuccess();
    } catch (error) {
      console.log(error);
    }
  };

  const handleDeleteBtnClick = () => {
    if (window.confirm('주문을 취소하시겠습니까?')) {
      cancleOrder();
    }
  };

  return (
    <>
      <li key={orderId} className="list-group-item d-flex mt-3">
        <div className="d-flex col-md-10">
          <div className="col-md-4">
            <div className="row text-muted">{postcode}</div>
            <div className="row">{address}</div>
          </div>
          <div className="col-md-3 text-center price">{orderStatus}</div>
          <div className="col align-self-center text-center">
            {formatDate(createdAt)}
          </div>
        </div>
        {orderStatus !== 'ACCEPTED' ? null : (
          <div className="d-flex justify-content-end">
            <div className="col text-end action">
              <Link
                to="/order-update"
                state={{ id: orderId, email, address, postcode }}
              >
                <button className="btn btn-sm btn-outline-dark">수정</button>
              </Link>
            </div>
            <div className="col text-end action">
              <button
                onClick={handleDeleteBtnClick}
                className="btn btn-sm btn-outline-dark"
              >
                취소
              </button>
            </div>
          </div>
        )}
      </li>
    </>
  );
};

export default History;
