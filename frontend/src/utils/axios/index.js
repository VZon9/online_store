import axios from "axios";

export const instance = axios.create({
    baseURL: 'http://25.48.185.126:8080/',
    timeout: 100000
});