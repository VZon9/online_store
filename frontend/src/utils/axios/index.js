import axios from "axios";

export const instance = axios.create({
    baseURL: 'http://26.134.246.98:8080/',
    timeout: 100000
});