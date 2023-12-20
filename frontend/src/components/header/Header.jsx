import React from 'react';
import logoImg from '../../img/logologo.png'
import './style.css'
import {Button} from "@mui/material";
import {Link, useNavigate} from "react-router-dom";
import ShoppingCartOutlinedIcon from '@mui/icons-material/ShoppingCartOutlined';

const Header = () => {
    let to = ''
    if (localStorage.getItem("isAuth") === 'true'){
        to = '/profile'
    }else {
        to = '/login'
    }
    const navigate = useNavigate();
    return (
        <header className="header">
            <div className="container">
                <div className="header_row">
                    <div className="header_logo">
                        <img className="logoimg" src={logoImg} alt="logo"/>
                        <span><Link style = {{color:'black', textDecoration: 'none'}} to="/home">BUBNASHOP</Link></span>
                    </div>
                    <nav className="header_nav">
                        <ul>
                            <li style={{cursor: 'pointer'}}
                                onClick={() => {
                                navigate("/cart")
                            }}><ShoppingCartOutlinedIcon/></li>
                            <li>
                                <Button variant="contained"  color = "secondary" size="large" sx ={{
                                textAlign: 'center',
                                fontWeight: '500',
                                fontSize: '24px',
                                lineHeight: '1.5',
                                letterSpacing: '-0.03em',
                                padding: '17px 22px 17px',
                                borderRadius: '7px'
                            }}>
                                <Link style = {{color:'white'}} to={to}>{localStorage.getItem("isAuth") === 'true' ? localStorage.getItem("username") : 'sign in!'}</Link>
                            </Button>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </header>
    );
};

export default Header;