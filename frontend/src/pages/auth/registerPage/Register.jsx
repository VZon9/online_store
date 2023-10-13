import React from 'react';
import {Button, TextField, Typography} from "@mui/material";
import LoginIcon from "@mui/icons-material/Login";
import {Link} from "react-router-dom";

const RegisterPage = (props) => {

    const {setEmail, setLogin, setPassword, setRepeatPassword, register, errors} = props

    return (
        <div className="form-box">
            <Typography variant="h4" component="h2" padding = {2} textAlign = 'center' fontFamily="Poppins">
                SIGN UP
            </Typography>
            <TextField fullWidth={true} margin="normal"  label="email" variant="outlined" placeholder="enter your email here"
                // onChange={(e) => setEmail(e.target.value)}
                       error={!!errors.email}
                       helperText={errors.email ? errors.email.message : ''}
                       {...register("email",{
                           required:"this field is required"
                       })}
            />
            <TextField  fullWidth={true} margin="normal"  label="login" variant="outlined" placeholder="enter your login here"
                // onChange={(e) => setLogin(e.target.value)}
                        error={!!errors.login}
                        helperText={errors.login ? errors.login.message : ''}
                        {...register("login",{
                            required:"this field is required"
                        })}
            />
            <TextField type="password"  fullWidth={true}  margin="normal" label="password" variant="outlined" placeholder="enter your password here"
                // onChange={(e) => setPassword(e.target.value)}
                       error={!!errors.password}
                       helperText={errors.password ? errors.password.message : ''}
                       {...register("password",{
                           required:"this field is required"
                       })}
            />
            <TextField type="password"  fullWidth={true}  margin="normal" label="password" variant="outlined" placeholder="re-enter your password here"
                // onChange={(e) => setRepeatPassword(e.target.value)}
                       {...register("password",{
                           required:"this field is required"
                       })}
            />
            <Button variant="contained" endIcon={<LoginIcon />} sx = {{width: '60%', margin: '0 auto', display: "flex"}} color = "secondary" type = "submit">
                sign up
            </Button>
            <Typography variant="body1" component="p" textAlign = 'center' sx = {{marginTop: '5px'}}>
                already have an account? <Link to="/login" className="link-auth">sign in</Link>
            </Typography>
        </div>
    );
};

export default RegisterPage;