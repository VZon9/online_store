import React from 'react';
import card1 from '../../img/home_cards/card1.jpg'
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import './style.css'
const PopularCard = (props) => {
    return (
        <div className="popular_card">
            <img src={card1} alt="card" className="popular_card_img" />
            <div className="popular_card_body">
                <div className="popular_card_text">
                    <div className="popular_card_header">{props.title}</div>
                    <div className="popular_card_desc">{props.description}</div>
                    <div className="popular_card_price">{props.price}</div>
                </div>
                <div className="popular_card_icon">
                    <ArrowForwardIosIcon/>
                </div>
            </div>
        </div>
    );
};

export default PopularCard;