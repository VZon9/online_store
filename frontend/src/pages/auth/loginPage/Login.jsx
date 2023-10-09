import React from 'react';
import {Button, TextField, Typography} from "@mui/material";
import LoginIcon from '@mui/icons-material/Login';
import {Link} from "react-router-dom";

const LoginPage = (props) => {

    const {setEmail, setPassword} = props

    return (
        <div className="form-box">
            <Typography variant="h4" component="h2" padding = {2} textAlign = 'center' fontFamily='Poppins'>
                SIGN IN
            </Typography>
            <TextField
                required fullWidth={true} margin="normal"  label="email"
                variant="outlined" placeholder="enter your email here"
                onChange={(e) => setEmail(e.target.value)}
            />

            <TextField type="password" required fullWidth={true} margin="normal"
               id="standard-basic" label="password" variant="outlined"
               placeholder="enter your password here"
               onChange={(e)=>setPassword(e.target.value)}
            />

            <Button variant="contained" endIcon={<LoginIcon />} sx = {{width: '60%', margin: '0 auto', display: "flex"}} color = "secondary" type = "submit">
                sign in
            </Button>

            <Typography variant="body1" component="p" textAlign = 'center' sx = {{marginTop: '5px'}}>
                don't have an account? <Link to="/register" className="link-auth"> create an account</Link>
            </Typography>
        </div>
    );
};

export default LoginPage;