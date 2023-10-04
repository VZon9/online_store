import React from 'react';
import logoImg from './../../img/logologo.png'
import './style.css'
import {Button} from "@mui/material";
import {Link} from "react-router-dom";

const Header = () => {
    return (
        <header className="header">
            <div className="container">
                <div className="header_row">
                    <div className="header_logo">
                        <img className="logoimg" src={logoImg} alt="logo"/>
                        <span>BubnaShop</span>
                    </div>
                    <nav className="header_nav">
                        <ul>
                            <li><a href="#!">FOR MEN</a></li>
                            <li><a href="#!">FOR WOMEN</a></li>
                            <li><a href="#!">CART</a></li>
                            <li><a href="#!">PROFILE</a></li>
                            {/*<li><a href="#!" className="header_nav-btn">SIGN UP</a></li>*/}
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
                                <Link style = {{color:'white'}} to="/login"> sign in</Link>
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