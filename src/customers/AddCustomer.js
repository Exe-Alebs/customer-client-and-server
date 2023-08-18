import axios from 'axios';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function AddCustomer() {
  let navigate = useNavigate();
  const [Customer, setCustomer] = useState({
    username: '',
    fullName: '',
    email: '',
  });

  const { username, fullName, email } = Customer;

  const onInputChange = (e) => {
    setCustomer({ ...Customer, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post('http://localhost:8080/customer', Customer);
    navigate('/');
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Register Customer</h2>
          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Username" classname="form-label">
                Username
              </label>
              <input
                required
                type={'text'}
                placeholder="Enter your username"
                className="form-control"
                name="username"
                value={username}
                onChange={(e) => {
                  onInputChange(e);
                }}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="FullName" classname="form-label">
                full-Name
              </label>
              <input
                required
                type={'text'}
                placeholder="Enter your fullname"
                className="form-control"
                name="fullName"
                value={fullName}
                onChange={(e) => {
                  onInputChange(e);
                }}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Email" classname="form-label">
                E-mail
              </label>
              <input
                required
                type={'text'}
                placeholder="Enter your e-mail address"
                className="form-control"
                name="email"
                value={email}
                onChange={(e) => {
                  onInputChange(e);
                }}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Submit
            </button>
            <Link to="/" type="submit" className="btn btn-outline-danger mx-2">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
