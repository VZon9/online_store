import React from 'react';
import {Button, TextField, Typography} from "@mui/material";
import LoginIcon from "@mui/icons-material/Login";
import {Link} from "react-router-dom";
const Test = () => {
    return (
        <div className="form-box">
            <Typography variant="h4" component="h2" padding = {2} textAlign = 'center' fontFamily='Poppins'>
                TEST
            </Typography>

            <Button variant="contained" sx = {{width: '60%', margin: '0 auto', display: "flex"}} color = "secondary" type = "submit">
                TEST
            </Button>
        </div>
    );
};

export default Test;