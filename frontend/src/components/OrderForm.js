import React from 'react';

const OrderForm = ({
  order,
  handleEmailChanged,
  handleAddressChanged,
  handlePostcodeChanged,
}) => {
  return (
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
          disabled={!handleEmailChanged}
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
  );
};

export default OrderForm;
