const formatPrice = price => {
  const formatter = new Intl.NumberFormat('ko-Kr', {
    style: 'currency',
    currency: 'KRW',
  });
  return formatter.format(price);
};

export default formatPrice;
