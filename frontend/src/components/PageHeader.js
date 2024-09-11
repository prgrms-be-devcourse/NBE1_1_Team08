import React from 'react';
import { Link } from 'react-router-dom';

const PageHeader = () => {
  return (
    <div className="row justify-content-center m-4">
      <h1 className="text-center">Grids & Circle</h1>
      <div className="d-grid gap-2 d-md-flex justify-content-md-end page-header">
        <Link to="/">
          <button className="btn btn-dark">Main</button>
        </Link>
      </div>
    </div>
  );
};

export default PageHeader;
