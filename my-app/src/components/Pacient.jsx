import React, {useEffect, useState} from "react";
import {Outlet, useParams} from "react-router-dom";
import {api} from "../services/Axios";

const Pacient = () => {
    const { email } = useParams();
    const [pacient, setPacient] = useState({});
    const [zdravniki, setZdravniki] = useState([]);
    const [izbran, setIzbran] = useState("");

    useEffect(() => {
        const pridobiPacienta = () => {
            api.get(`/pacienti/${email}`).then((response) => {
                setPacient(response.data);
                console.log(response.data)
            })
        }
        const pridobiZdravnike = () => {
            api.get(`/zdravniki`).then((response) => {
                setZdravniki(response.data);
            })
        }
        pridobiPacienta();
        pridobiZdravnike();
    }, []);

    function handleClick () {
        console.log("izbran: " + izbran)
        api.put(`/pacienti/${pacient.email}/dodajZdr/${izbran}`, {}).then((response) => {
            console.log("uspesno")
            window.location.reload(true);
        }).catch(err => console.error(err))
    }
    const hendlajSelect = (event) => {
        event.preventDefault();
        const izbrana = event.target.value;
        console.log(izbrana);
        setIzbran(izbrana);
    }

  return(
      <div style={{marginLeft: "100px", marginTop: "30px"}} >
          Ime: {pacient.ime} <br />
          Priimek: {pacient.priimek} <br />
          Email: {pacient.email} <br />
          Posebnosti: {pacient.posebnosti} <br />
          Osebni zdravnik: {pacient.osebniZdravnik === undefined ? "Ni izbranega" : pacient.osebniZdravnik.email} <br />
          Izberi zdravnika:
          <select value={izbran} onChange={hendlajSelect}>
              {
                  zdravniki.map(zdravnik => {
                      return(
                          <option key={zdravnik.id} value={zdravnik.id}>
                              {zdravnik.email}
                          </option>
                      )
                  })
              }
          </select>
          <button onClick={handleClick}>Potrdi</button>
      </div>
  )
}

export default Pacient;