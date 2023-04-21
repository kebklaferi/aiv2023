import React, {useEffect, useState} from "react";
import {api} from "../services/Axios";
import {Link} from "react-router-dom";

const Zdravniki = () => {
    const [zdravniki, setZdravniki] = useState([]);

    useEffect(() => {
        const pridobiZdravnike = () => {
            api.get(`/zdravniki`).then((response) => {
                setZdravniki(response.data);
                console.log(response.data)
            })
        }
        pridobiZdravnike();
    }, []);

    return(<div>
        <h1 style={{textAlign: "center"}} >Zdravniki</h1>
        <div style={{marginLeft: "100px", marginTop: "30px"}}>
            {
                zdravniki.map((zdravnik) => {
                    return(
                        <li key={zdravnik.id}>
                            <Link to={zdravnik.email}>{zdravnik.email}</Link>
                        </li>
                    )
                })}
        </div>
    </div>)
}
export default Zdravniki;