import React from 'react';
import card1 from './../../img/home_cards/card1.jpg'
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import './style.css'
const Card = () => {
    return (
        <div className="card">
            <img src={card1} alt="card" className="card_img" />
            <div className="card_body">
                <div className="card_text">
                    <div className="card_header">HEADER</div>
                    <div className="card_desc">take a look!</div>
                </div>
                <div className="card_icon">
                    <ArrowForwardIosIcon/>
                </div>
            </div>
        </div>
    );
};

export default Card;