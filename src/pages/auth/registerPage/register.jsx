import React from 'react';
import {Button, TextField, Typography} from "@mui/material";
import LoginIcon from "@mui/icons-material/Login";
import {Link} from "react-router-dom";

const RegisterPage = (props) => {

    const {setEmail, setUsername, setPassword} = props

    return (
        <div className="form-box">
            <Typography variant="h4" component="h2" padding = {2} textAlign = 'center'>
                SIGN UP
            </Typography>
            <TextField required fullWidth={true} defaultValue="niggers@gmail.com" margin="normal"  label="email" variant="outlined" placeholder="enter your email here"
                       onChange={(e) => setEmail(e.target.value)}
            />
            <TextField required fullWidth={true} defaultValue="myUsername" margin="normal"  label="username" variant="outlined" placeholder="enter your username here"
                       onChange={(e) => setUsername(e.target.value)}
            />
            <TextField type="password" required fullWidth={true} defaultValue="ihateniggers" margin="normal"  label="password" variant="outlined" placeholder="enter your password here"
                       onChange={(e) => setPassword(e.target.value)}
            />
            <TextField type="password" required fullWidth={true} defaultValue="ihateniggers" margin="normal" label="password" variant="outlined" placeholder="re-enter your password here"
                       onChange={(e) => setPassword(e.target.value)}
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