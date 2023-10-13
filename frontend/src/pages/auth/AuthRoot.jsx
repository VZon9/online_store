import React, {useState} from 'react';
import './style.css'
import {useLocation} from "react-router-dom";
import LoginPage from "./loginPage/Login";
import RegisterPage from "./registerPage/Register";
import {Box} from "@mui/material";
import {instance} from "../../utils/axios";
import {useForm} from "react-hook-form";


// const ACCESS_TOKEN_KEY = "access_token"
const AuthRoot = () => {

    const [email, setEmail] = useState('')
    const [login, setLogin] = useState('')
    const [password, setPassword] = useState('')
    const [repeatPassword, setRepeatPassword] = useState('')

    const location = useLocation();

    const {register, formState:{errors}, handleSubmit} = useForm()

    const handleSubmitForm = async (data) => {
        // e.preventDefault();
        if (location.pathname === '/login'){
            const userData = {
                login: data.login,
                password: data.password
            }
            const response = await instance.post('login', userData)
            console.log(response.data)
            if (response.status === 200){
                instance.headers = {'Authorization': response.data.token}
                // localStorage.setItem(ACCESS_TOKEN_KEY, response.data.token)
                console.log(response.data)
            }else {
                alert("Upd the ")
            }

        }else {
            const userData = {
                email: data.email,
                login: data.login,
                password: data.password
            }
            const newUser = await instance.post('register', userData)
            console.log(newUser)
        }
    }

    return (
        <div className='root'>
            <form className='form' onSubmit={handleSubmit(handleSubmitForm)}>
                <Box sx = {{
                    display: 'flex',
                    justifyContent: 'center',
                    flexDirection: 'column',
                    margin: 'auto',
                    padding: 5,
                    borderRadius: 2,
                    boxShadow: '5px 5px 10px #ccc',
                    width: '30%',
                }}>
                    {location.pathname === '/login' ?
                        <LoginPage setEmail={setEmail} setPassword={setPassword} register = {register} />  : location.pathname === '/register' ?
                            <RegisterPage setEmail={setEmail} setLogin={setLogin} setPassword={setPassword}  setRepeatPassword = {setRepeatPassword} register = {register} errors = {errors}/> : null}
                </Box>
            </form>

        </div>
    );
};

export default AuthRoot;