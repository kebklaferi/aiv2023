import React from "react";
import {createBrowserRouter} from "react-router-dom";
import App from "../App";
import Pacienti from "../components/Pacienti";
import Zdravniki from "../components/Zdravniki";
import Pacient from "../components/Pacient";
import Zdravnik from "../components/Zdravnik";

 export const Ruter = createBrowserRouter([
    {
        path: "/",
        element: <App />,
        children: [
            {
                path: "zdravniki",
                element: <Zdravniki />,
            },
            {
                path: "zdravniki/:email",
                element: <Zdravnik />
            },
            {
                path: "pacienti",
                element: <Pacienti />,
            },
            {
                path: "pacienti/:email",
                element: <Pacient />
            }
        ]
    }
])