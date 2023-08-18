import React from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';

export default function ViewCustomer() {
  const [Customer, setCustomer] = useState({
    username: '',
    fullName: '',
    email: '',
  });

  const { id } = useParams();

  useEffect(() => {
    loadCustomer();
  }, []);

  const loadCustomer = async () => {
    const result = await axios.get(`http://localhost:8080/customer/${id}`);
    setCustomer(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Customer Details</h2>
          <div className="card">
            <div className="card-header">
              Details of Customer id :{Customer.id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Name:</b>
                  {Customer.username}
                </li>
                <li className="list-group-item">
                  <b>UserName:</b>
                  {Customer.fullName}
                </li>
                <li className="list-group-item">
                  <b>Email:</b>
                  {Customer.email}
                </li>
              </ul>
            </div>
          </div>
          <Link className="btn btn-primary my-2" to={'/'}>
            Back to Home
          </Link>
        </div>
      </div>
    </div>
  );
}
