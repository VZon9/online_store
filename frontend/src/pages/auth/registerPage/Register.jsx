import React from 'react';
import {Button, TextField, Typography} from "@mui/material";
import {Link} from "react-router-dom";

const RegisterPage = (props) => {

    const {register, errors, watch} = props

    return (
        <div className="form-box">
            <Typography variant="h4" component="h2" padding = {2} textAlign = 'center' fontFamily="Poppins">
                SIGN UP
            </Typography>
            <TextField
                fullWidth={true}
                margin="normal"
                label="email"
                variant="outlined"
                placeholder="enter your email here"
                error={!!errors.email}
                helperText={errors.email ? errors.email.message : ''}
                {...register("regEmail",{
                    required:"this field is required!"
                })}
            />
            <TextField
                fullWidth={true}
                margin="normal"
                label="login"
                variant="outlined"
                placeholder="enter your login here"
                defaultValue=''
                error={!!errors.login}
                helperText={errors.login ? errors.login.message : ''}
                {...register("regLogin",{
                    required:"this field is required!"
                })}
            />
            <TextField
                type="password"
                fullWidth={true}
                margin="normal"
                label="password"
                variant="outlined"
                placeholder="enter your password here"
                error={!!errors.password}
                helperText={errors.password ? errors.password.message : ''}
                {...register("regPassword",{
                    required:"this field is required!",
                    message:"Invalid data"
                })}
            />
            {/*<TextField type="password"  fullWidth={true}  margin="normal" label="password" variant="outlined" placeholder="re-enter your password here"*/}
            {/*           {...register("password",{*/}
            {/*               required:"this field is required"*/}
            {/*           })}*/}
            {/*/>*/}
            <Button variant="contained"  sx = {{width: '60%', margin: '0 auto', display: "flex"}} color = "secondary" type = "submit" disabled = {watch("regEmail", '') === '' || watch("regLogin", '') === '' || watch("regPassword", '') === ''}>
                sign up
            </Button>
            <Typography variant="body1" component="p" textAlign = 'center' sx = {{marginTop: '5px'}}>
                already have an account? <Link to="/login" className="link-auth">sign in</Link>
            </Typography>
        </div>
    );
};

export default RegisterPage;