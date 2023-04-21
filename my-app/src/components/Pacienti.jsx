import React, {useEffect, useState} from "react";
import {api} from "../services/Axios";
import {Link, Outlet} from "react-router-dom";

const Pacienti = () => {
  const [pacienti, setPacienti] = useState([]);

  useEffect(() => {
      const pridobiPaciente = () => {
          api.get(`/pacienti`).then((response) => {
              setPacienti(response.data);
              console.log(response.data)
          })
      }
      pridobiPaciente();
  }, []);

  return(
      <div>
          <h1 style={{textAlign: "center"}} >Pacienti</h1>
          <div style={{marginLeft: "100px", marginTop: "30px"}}>
              {
                  pacienti.map((pacient) => {
                      return(
                          <li key={pacient.id}>
                              <Link to={pacient.email}>{pacient.email}</Link>
                          </li>
                      )
                  })}
          </div>
  </div>)
}
export default Pacienti;