import React from 'react';
import {Link, Outlet} from "react-router-dom";

function App() {
  return (
    <div>
      <h1 style={{textAlign: "center"}}>E-ZDRAVJE</h1>
      <div style={{textAlign: "center"}}>
        <Link style={{padding: "10px", backgroundColor: "whitesmoke", color: "black", marginRight: "5px"}} to={"/"}>Zacetna stran</Link>
        <Link style={{padding: "10px", backgroundColor: "whitesmoke", color: "black", marginRight: "5px"}} to={"zdravniki"}>Zdravniki</Link>
        <Link style={{padding: "10px", backgroundColor: "whitesmoke", color: "black", marginLeft: "5px"}} to={"pacienti"}>Pacienti</Link>
      </div>
        <Outlet />
    </div>
  );
}

export default App;
