import React from 'react';
import promoImg from './../../img/promo2.jpg'
import './style.css'
import {Button} from "@mui/material";
const Promo = () => {
    return (
        <section className="promo">
            <div className="container">
                <div className="promo_content">

                    <div className="promo_text">
                        <div className="promo-title">
                            THE BEST SNEAKERS FROM ALL OVER THE WORLD
                        </div>
                        <div className="promo-desc">TAKE THE PAIR YOU ACTUALLY WANT</div>
                        <div className="promo_btn-wrapper">
                            <Button variant="contained"  color = "secondary" size="medium" sx ={{
                                textAlign: 'center',
                                fontWeight: '500',
                                fontSize: '30px',
                                lineHeight: '1.5',
                                letterSpacing: '-0.05em',
                                padding: '7px 12px 7px',
                                borderRadius: '7px'
                            }}>
                                SHOP NOW
                            </Button>
                        </div>
                    </div>

                    <div className="promo_img"><img src={promoImg} alt="promo-img" className="promo_nikeimg"/></div>

                </div>
            </div>

        </section>
    );
};

export default Promo;