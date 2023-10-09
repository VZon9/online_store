import React, {useState} from 'react';
import './style.css'
import {useLocation} from "react-router-dom";
import LoginPage from "./loginPage/Login";
import RegisterPage from "./registerPage/Register";
import {Box} from "@mui/material";

const AuthRoot = () => {

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [repeatPassword, setRepeatPassword] = useState('')
    const [username, setUsername] = useState('')

    const location = useLocation();

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log(email)
        console.log(password)
        console.log(repeatPassword)
        console.log(username)
    }

    return (
        <div className='root'>
            <form className='form' onSubmit={handleSubmit}>
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
                        <LoginPage setEmail={setEmail} setPassword={setPassword}/> : location.pathname === '/register' ?
                        <RegisterPage setEmail={setEmail} setPassword={setPassword}  setRepeatPassword = {setRepeatPassword} setUsername ={setUsername}/> : null}
                </Box>
            </form>

        </div>
    );
};

export default AuthRoot;