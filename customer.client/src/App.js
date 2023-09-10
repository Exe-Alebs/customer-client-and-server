import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AddCustomer from './customers/AddCustomer';
import EditCustomer from './customers/EditCustomers';
import ViewCustomer from './customers/ViewCustomer';

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />

        <Routes>
          <Route path="/" element={<Home />}></Route>
          <Route path="/addCustomer" element={<AddCustomer />}></Route>
          <Route path="/editCustomer/:id" element={<EditCustomer />}></Route>
          <Route path="/viewCustomer/:id" element={<ViewCustomer />}></Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
