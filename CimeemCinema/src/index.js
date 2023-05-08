import React from "react";
import ReactDOM from "react-dom/client";
// import './index.css';
import "../src/assets/css/style.css";
import BuyTicket from './pages/BuyTicket/index.jsx'
import Home from './pages/Home/index.jsx'
// Cấu hình react-router-dom
import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomeTemplates from "./HomeTemplates/index.jsx";
import { Provider } from "react-redux";
import "./admin_source/scss/style.scss";
import store from "./store";
const root = ReactDOM.createRoot(document.getElementById("root"));
// const loading = (
//   <div className="pt-3 text-center">
//     <div className="sk-spinner sk-spinner-pulse"></div>
//   </div>
// );

// Containers
const DefaultLayout = React.lazy(() =>
  import("./admin_source/layout/DefaultLayout")
);

// Pages
const Login = React.lazy(() =>
  import("./admin_source/views/pages/login/Login")
);
const Register = React.lazy(() =>
  import("./admin_source/views/pages/register/Register")
);
const Page404 = React.lazy(() =>
  import("./admin_source/views/pages/page404/Page404")
);
const Page500 = React.lazy(() =>
  import("./admin_source/views/pages/page500/Page500")
);
// import Home from './pages/Home/index';
// import BuyTicket from './pages/BuyTicket/index';

root.render(
  <Provider store={store}>
    <BrowserRouter>
      <Routes>
        <Route path="" element={<HomeTemplates />}>
        <Route index path="" element={<Home/>}></Route>
          <Route path="buyticket" element={<BuyTicket />}></Route>
        </Route>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/404" element={<Page404 />} />
        <Route path="/500" element={<Page500 />} />
        <Route path="/admin/*" element={<DefaultLayout />} />
        {/* </Route> */}

        <Route></Route>
        <Route></Route>
        <Route></Route>
        <Route></Route>
        <Route></Route>
        <Route></Route>
      </Routes>
    </BrowserRouter>
  </Provider>
);
