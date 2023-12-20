import React from 'react';
import {Button, TextField, Typography} from "@mui/material";
import LoginIcon from '@mui/icons-material/Login';
import {Link} from "react-router-dom";
import {useForm} from "react-hook-form";

const LoginPage = (props) => {
    useForm({
        defaultValues: {
            login: '',
            password: ''
        }
    })
    const {register, errors, watch, errMessage} = props

    return (
        <div className="form-box">
            <Typography variant="h4" component="h2" padding = {2} textAlign = 'center' fontFamily='Poppins'>
                SIGN IN
            </Typography>
            <TextField
                fullWidth={true}
                margin="normal"
                label="login"
                variant="outlined"
                placeholder="enter your login here"
                defaultValue=''
                error={!!errors.login}
                helperText={errors.email ? errors.login.message : ''}
                {...register("login",{
                    required: "Login is required"
                })}
            />

            <TextField
                type="password"
                fullWidth={true}
                margin="normal"
                id="standard-basic"
                label="password"
                variant="outlined"
                placeholder="enter your password here"
                defaultValue=''
                error={!!errors.password}
                helperText={errors.email ? errors.password.message : ''}
                {...register("password",{
                    required: "Password is required"
                })}
            />

            <Button
                variant="contained"
                endIcon={<LoginIcon />}
                sx = {{width: '60%', margin: '0 auto', display: "flex"}}
                color = "secondary"
                type = "submit"
                disabled = {watch("login", '') === '' || watch("password", '') === ''}>
                sign in
            </Button>

            <Typography variant="body1" component="p" textAlign = 'center' sx = {{marginTop: '5px'}}>
                don't have an account? <Link to="/register" className="link-auth"> create an account</Link>
            </Typography>
            <Typography variant="body2" component="p" textAlign = 'center' sx = {{marginTop: '5px'}} color = 'red'>
                {errMessage}
            </Typography>
        </div>
    );
};

export default LoginPage;