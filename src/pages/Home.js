import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import ReactPaginate from 'react-paginate';

export default function Home() {
  const [Customers, setCustomers] = useState([]);
  const [query, setQuery] = useState('');

  useEffect(() => {
    loadCustomers(1, 4);
  }, []);

  const loadCustomers = async (page, size) => {
    const results = await axios.get('http://localhost:8080/customers', {
      params: {
        page: page,
        size: size,
      },
    });
    //loadCustomers(1, 4);

    console.log(results);
    setCustomers(results.data.customers);
  };
  const deleteCustomer = async (id) => {
    await axios.delete(`http://localhost:8080/customer/${id}`);
    loadCustomers();
  };
  const fetchCustomers = async (currentPage, size) => {
    const res = await axios.get(`http://localhost:8080/customers`, {
      params: {
        page: currentPage,
        size: size,
      },
    });
    const results = await res.data.customers;
    return results;
  };
  const handlePageClick = async (data) => {
    console.log(data.selected);
    let currentPage = data.selected + 1;
    const customersfromServer = await fetchCustomers(currentPage, 4);
    setCustomers(customersfromServer);
  };
  return (
    <div>
      <div className="container">
        <div className="py-4">
          <input
            type="text"
            placeholder="Search..."
            className="search"
            onChange={(e) => setQuery(e.target.value)}
          />
          <table className="table bor der shadow">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Username</th>
                <th scope="col">Full-Name</th>
                <th scope="col">email</th>
                <th scope="col">Action</th>
              </tr>
            </thead>
            <tbody>
              {Customers.filter((customer) =>
                customer.username.toLowerCase().includes(query)
              ).map((customer, index) => (
                <tr key={customer.id}>
                  <th scope="row" key={index}>
                    {index + 1}
                  </th>
                  <td>{customer.username}</td>
                  <td>{customer.fullName}</td>
                  <td>{customer.email}</td>
                  <td>
                    <Link
                      to={`/viewCustomer/${customer.id}`}
                      className="btn btn-primary mx-2"
                    >
                      View
                    </Link>
                    <Link
                      className="btn btn-outline-primary mx-2"
                      to={`/editCustomer/${customer.id}`}
                    >
                      Edit
                    </Link>
                    <button
                      className="btn btn-danger mx-2"
                      onClick={() => deleteCustomer(customer.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
      <div>
        <ReactPaginate
          previousLabel={'<<'}
          nextLabel={'>>'}
          breakLabel={'...'}
          pageCount={9}
          marginPagesDisplayed={3}
          pageRangeDisplayed={2}
          onPageChange={handlePageClick}
          containerClassName={'pagination justify-content-center'}
          pageClassName={'page-item'}
          pageLinkClassName={'page-link'}
          previousClassName={'page-item'}
          previousLinkClassName={'page-link'}
          nextClassName={'page-item'}
          nextLinkClassName={'page-link'}
          breakClassName={'page-item'}
          breakLinkClassName={'page-link'}
          activeClassName={'active'}
        />
      </div>
    </div>
  );
}
