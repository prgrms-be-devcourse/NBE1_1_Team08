const calculateTotalPrice = items => {
  return items.reduce((prev, curr) => prev + curr.price * curr.quantity, 0);
};

export default calculateTotalPrice;
