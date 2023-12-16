import React, {useContext, useState} from 'react';
import './style.css'
import card1 from '../../img/miniImgs/yeezy350v2_1.jpg'
import ClearIcon from '@mui/icons-material/Clear';
import {CartContext} from "../../contexts/CartContext/CartContext";
import Checkbox from "@mui/material/Checkbox";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";
const CartItem = (props) => {
    const {rmFromCart} = useContext(CartContext)
    const {brand, model, price, id, size, img, type, quantity, totalPrice, setTotalPrice,  totalCnt, setTotalCnt, selectedForOrder} = props
    const image = 'http://26.134.246.98:8080/img/' + img + '_main.png'
    const refreshCartInfo = (isChecked) => {
        if (isChecked) {
            setTotalPrice(totalPrice + price*quantity)
            setTotalCnt(totalCnt + quantity)
            selectedForOrder.current.push(id)
        }else {
            setTotalPrice(totalPrice - price*quantity)
            setTotalCnt(totalCnt - quantity)
            const index = selectedForOrder.current.indexOf(id)
            selectedForOrder.current.splice(index, 1)
        }
        console.log(selectedForOrder)
    }


    return (
        <section className="cart_item">
            <div className="cart_item_wrapper">
                <div className="title_price">
                    <div className="cart_item_title">
                        {type}
                    </div>
                    <div className="cart_item_price">
                        {price}
                    </div>
                </div>
                <hr className="cart_item_hr" />
                <div className="cart_item_body">
                        <Checkbox
                            size="small"
                            color="secondary"
                            onChange={e => {
                                refreshCartInfo(e.target.checked)
                            }}
                    />
                    <div className="cart_item_img_box">
                        <img src={image} alt='img' className="cart_item_img"/>
                    </div>
                    <div className="cart_item_info">
                        <h1 className="cart_item_cat-name"> PRODUCT </h1>
                        <h3> {brand} </h3>
                        <h4> {model} </h4>
                    </div>

                    <div className="cart_item_quantity">
                        <h1 className="cart_item_cat-name"> QUANTITY </h1>
                        <div className="cart_item-count"> {quantity} </div>
                    </div>

                    <div className="cart_item_size">
                        <h1 className="cart_item_cat-name"> SIZE </h1>
                        <div className="cart_item-size"> {size} </div>
                    </div>

                    <div className="cart_item_remove">
                        <h1 className="cart_item_cat-name"> REMOVE </h1>
                        <ClearIcon sx = {{
                            cursor: 'pointer'
                        }}
                                   onClick={() => {
                            rmFromCart(id)
                        }}/>
                    </div>

                </div>
            </div>
        </section>
    );
};

export default CartItem;