import { useRoutes } from "react-router-dom";
import { ToastContainer } from 'react-toastify';
import Themeroutes from "./routes/Router";

import 'react-toastify/dist/ReactToastify.css';
import 'bootstrap/dist/css/bootstrap.min.css';

const App = () => {
  const routing = useRoutes(Themeroutes);
  return <div className="dark">
    {routing}
    <ToastContainer />
  </div>;
};

export default App;
