import React, { useState } from 'react';
import { SummaryItem } from './SummaryItem';
import axios from 'axios';

export function Summary({ items = [] }) {
  const totalPrice = items.reduce(
    (prev, curr) => prev + curr.price * curr.count,
    0,
  );
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

    console.log(items);
    axios
      .post('http://localhost:8080/api/v1/orders', {
        email: order.email,
        address: order.address,
        postcode: order.postcode,
        orderItems: items.map(v => ({
          productId: v.id,
          category: v.category,
          price: v.price,
          quantity: v.count,
        })),
      })
      .then(
        () => alert('주문이 정상적으로 접수되었습니다.'),
        () => alert('주문에 실패했습니다.'),
      );
    // onOrderSubmit(order);
  };

  return (
    <React.Component>
      <div>
        <h5 className="m-0 p-0">
          <b>Summary</b>
        </h5>
      </div>
      <hr />
      {items.map(v => (
        <SummaryItem key={v.id} name={v.name} count={v.count} />
      ))}
      <form>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">
            이메일
          </label>
          <input
            type="email"
            className="form-control mb-1"
            id="email"
            value={order.email}
            onChange={handleEmailChanged}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="address" className="form-label">
            주소
          </label>
          <input
            type="text"
            className="form-control mb-1"
            id="address"
            value={order.address}
            onChange={handleAddressChanged}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="postcode" className="form-label">
            우편번호
          </label>
          <input
            type="text"
            className="form-control"
            id="postcode"
            value={order.postcode}
            onChange={handlePostcodeChanged}
          />
        </div>
        <div>당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.</div>
      </form>
      <div className="row pt-2 pb-2 border-top">
        <h5 className="col">총금액</h5>
        <h5 className="col text-end">{totalPrice}원</h5>
      </div>
      <button className="btn btn-dark col-12" onClick={handleSubmit}>
        결제하기
      </button>
    </React.Component>
  );
}
