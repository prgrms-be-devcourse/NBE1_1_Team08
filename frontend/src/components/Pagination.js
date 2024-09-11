import React from 'react';
import ReactPaginate from 'react-paginate';
import { FiChevronLeft, FiChevronRight } from 'react-icons/fi';

const Pagination = ({ pageCount, onPageChange }) => {
  return (
    <ReactPaginate
      previousLabel={<FiChevronLeft />}
      nextLabel={<FiChevronRight />}
      pageCount={pageCount}
      onPageChange={onPageChange}
      containerClassName={'pagination'}
      pageLinkClassName={'pagination-link'}
      activeLinkClassName={'pagination-link-active'}
    />
  );
};

export default Pagination;
