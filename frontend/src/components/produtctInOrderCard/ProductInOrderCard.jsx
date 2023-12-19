import React from 'react';
import './style.css'
const ProductInOrderCard = (props) => {
    const {brand, model, size, img, productPrice} = props
    const image = 'http://26.134.246.98:8080/img/' + img + '_main.png'
    return (
        <div className="product_in_order">
            <div className="product_text">
                <p>{brand} {model} </p>
                <p>Size : {size}</p>
            </div>
            <div className="image_box">
                <img src={image} alt="img" className="image"/>
            </div>
            <div className="product_price">
                <p>${productPrice}</p>
            </div>
        </div>
    );
};

export default ProductInOrderCard;