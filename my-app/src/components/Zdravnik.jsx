import React, {useEffect, useState} from "react";
import {Outlet, useParams} from "react-router-dom";
import {api} from "../services/Axios";

const Zdravnik = () => {
    const { email } = useParams();
    const [zdravnik, setZdravnik] = useState({});

    useEffect(() => {
        const pridobiZdravnika = () => {
            api.get(`/zdravniki/${email}`).then((response) => {
                setZdravnik(response.data);
                console.log(response.data)
            })
        }
        pridobiZdravnika();
    }, []);

    return(
        <div style={{marginLeft: "100px", marginTop: "30px"}} >
            Ime: {zdravnik.ime} <br />
            Priimek: {zdravnik.priimek} <br />
            Email: {zdravnik.email} <br />
            Kvota pacientov: {zdravnik.kvotaPacientov}
        </div>
    )
}

export default Zdravnik;