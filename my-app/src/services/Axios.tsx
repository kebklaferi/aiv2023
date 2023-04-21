import Axios from "axios";

export const api = Axios.create({
    baseURL: process.env.REACT_APP_URL,
    timeout: 30000,
    headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
    },
})