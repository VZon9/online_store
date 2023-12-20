import React, {useContext, useState} from 'react';
import './style.css'
import card1 from '../../img/miniImgs/yeezy350v2_1.jpg'
import ClearIcon from '@mui/icons-material/Clear';
import {CartContext} from "../../contexts/CartContext/CartContext";
import Checkbox from "@mui/material/Checkbox";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";
import AttachMoneyIcon from '@mui/icons-material/AttachMoney';
import {Button, Typography} from "@mui/material";
const CartItem = (props) => {
    const {rmFromCart} = useContext(CartContext)
    const {brand, model, price, id, size, img, type, color, quantity, totalPrice, setTotalPrice,  totalCnt, setTotalCnt, selectedForOrder} = props
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
                <div className="item_info">
                        <Checkbox
                            size="medium"
                            color="secondary"
                            onChange={e => {
                                refreshCartInfo(e.target.checked)
                            }}
                        />
                    <div className="cart_item_img_box">
                        <img src={image} alt='img' className="cart_item_img"/>
                    </div>
                    <div className="item_text">
                        <div className="item_name">
                            <p> {brand} {model} </p>
                        </div>

                        <div className="item_size_color">
                            <p>Size: {size}, Color: {color}</p>
                        </div>
                        <div className="item_qty">
                            <p>Quantity: {quantity}</p>
                        </div>
                        <div className="item_btn">
                            <Button variant="contained"  size="medium" onClick={() => rmFromCart(id)} sx = {{
                                textAlign: 'center',
                                borderRadius: '25px',
                                background: '#ef5350'
                            }}>
                                <Typography fontWeight='900' fontSize='14px' > Remove </Typography>
                            </Button>
                        </div>
                    </div>

                </div>
                <div className="item_price">
                     <p> ${price} </p>
                </div>
            </div>
        </section>
    );
};

export default CartItem;