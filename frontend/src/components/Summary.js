import React, { useState } from 'react';
import SummaryItem from './SummaryItem';
import axios from 'axios';
import OrderForm from './OrderForm';
import { formatPrice, calculateTotalPrice } from '../common';

const Summary = ({ items = [], handleMinusItem }) => {
  const totalPrice = calculateTotalPrice(items);
  const [order, setOrder] = useState({
    email: '',
    address: '',
    postcode: '',
  });

  const handleEmailChanged = e => {
    setOrder({ ...order, email: e.target.value });
  };
  const handleAddressChanged = e => {
    setOrder({ ...order, address: e.target.value });
  };
  const handlePostcodeChanged = e => {
    setOrder({ ...order, postcode: e.target.value });
  };
  const handleSubmit = () => {
    if (order.address === '' || order.email === '' || order.postcode === '') {
      alert('유효하지 않은 입력입니다.');
      return;
    }

    if (items.length === 0) {
      alert('주문할 상품을 선택해주세요.');
      return;
    }

    axios
      .post('http://localhost:8080/order/add', {
        email: order.email,
        address: order.address,
        postcode: order.postcode,
        orderItems: items.map(v => ({
          id: v.productId,
          quantity: v.quantity,
        })),
      })
      .then(
        () => alert('주문이 정상적으로 접수되었습니다.'),
        () => alert('주문에 실패했습니다.'),
      );
    // onOrderSubmit(order);
  };

  return (
    <>
      <div>
        <h5 className="m-0 p-0">
          <b>Summary</b>
        </h5>
      </div>
      <hr />
      <div className="summary-item-container p-1 mb-3">
        {items.map(v => (
          <SummaryItem
            key={v.productId}
            id={v.productId}
            name={v.productName}
            quantity={v.quantity}
            onMinusItemClicked={handleMinusItem}
          />
        ))}
      </div>
      <OrderForm
        order={order}
        handleEmailChanged={handleEmailChanged}
        handleAddressChanged={handleAddressChanged}
        handlePostcodeChanged={handlePostcodeChanged}
      />
      <div className="row pt-2 pb-2 border-top">
        <h5 className="col">총금액</h5>
        <h5 className="col text-end">{formatPrice(totalPrice)}</h5>
      </div>
      <button className="btn btn-dark col-12" onClick={handleSubmit}>
        결제하기
      </button>
    </>
  );
};

export default Summary;
