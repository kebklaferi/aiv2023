import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import {RouterProvider} from "react-router-dom";
import {Ruter} from "./routing/Ruter";

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <RouterProvider router={Ruter} />
  </React.StrictMode>
);