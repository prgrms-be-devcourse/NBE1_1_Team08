import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import { PageHeader, OrderForm } from '../components';
import axios from 'axios';

const OrderUpdatePage = () => {
  const location = useLocation();
  const { id, email, address, postcode } = location.state || {};
  const [order, setOrder] = useState({
    email: email,
    address: address,
    postcode: postcode,
  });

  const handleAddressChanged = e => {
    setOrder({ ...order, address: e.target.value });
  };
  const handlePostcodeChanged = e => {
    setOrder({ ...order, postcode: e.target.value });
  };
  const handleSubmit = async () => {
    try {
      await axios.put('http://localhost:8080/order/update', {
        id: id,
        email: order.email,
        address: order.address,
        postcode: order.postcode,
      });
    } catch (error) {
      console.log(error);
    }
    window.location.href = '/order-history';
  };

  return (
    <div className="container-fluid">
      <PageHeader />
      <div className="card">
        <div className="row">
          <div className="update-form p-4">
            <div>
              <h5 className="m-0 p-0">
                <b>Update Order</b>
              </h5>
            </div>
            <hr />
            <OrderForm
              order={order}
              handleAddressChanged={handleAddressChanged}
              handlePostcodeChanged={handlePostcodeChanged}
            />
            <button className="btn btn-dark col-12" onClick={handleSubmit}>
              수정하기
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default OrderUpdatePage;
