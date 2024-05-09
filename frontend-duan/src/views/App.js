import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../asset/css/pagination.css'

import { BrowserRouter, Routes, Route } from "react-router-dom";

import Client from '../components/client';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Client />}>

        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
