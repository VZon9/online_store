import React from 'react';
import card1 from '../../img/miniImgs/yeezy350v2_1.jpg'
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import './style.css'
const Card = (props) => {
    return (
        <div className="card">
            <div className="card_wrapper">
                <img src={card1} alt="card" className="card_img" />
                <div className="card_body">
                    <div className="card_text">
                        <div className="card_header_brand">{props.brand}</div>
                        <div className="card_header_model">{props.model}</div>
                        <div className="card_desc">{props.color}</div>
                        <div className="card_price">{props.price}</div>
                    </div>
                    <div className="card_icon">
                        <ArrowForwardIosIcon/>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Card;