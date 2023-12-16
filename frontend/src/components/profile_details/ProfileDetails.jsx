import React, {useEffect, useState} from 'react';
import "./style.css"
import {Button, TextField} from "@mui/material";
import 'dayjs/locale/ru'
import {instance} from "../../utils/axios";
import {ACCESS_TOKEN_KEY} from "../../pages/auth/AuthRoot";
import CartItem from "../cartItem/CartItem";
const ProfileDetails = () => {

    const [orders, setOrders] = useState([])
    useEffect(() => {
        const fetchOrders = async () => {
            const response = await instance.post('getOrders', {'userId': localStorage.getItem('userId')}, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem(ACCESS_TOKEN_KEY)}`
                }
            })
            const data = await response.data
            console.log('orders:', data)
            setOrders(data)
        }
        fetchOrders();
    }, []);

    return (
        <div>
            <section className="Profile_details">
                <div className="container">
                    <div className="content">
                        <div className="details_title">My details</div>
                        <div className="details_personal">My orders</div>
                        <hr className="profile_hr"/>
                        <div className="form_container">
                            <div className="form_text">Here is your history of orders. Thanks for choosing us.</div>
                        </div>
                        <div className="form_box">
                            <div className="orders_history">
                                {orders.map(item => {
                                    return(
                                        <div className="orders_history_card" key = {item.id}>
                                            <div className="orders_history_card_wrapper">
                                                <div className="order_text">
                                                    <div className="item_info">
                                                        <h4>order from</h4>
                                                        <p>{item.date}</p>
                                                    </div>
                                                    <div className="item_info">
                                                        <h4> order status </h4>
                                                        <p>{item.status}</p>
                                                    </div>
                                                </div>
                                                <div className="item_info">
                                                    <h4> order number </h4>
                                                    <p>{item.id}</p>
                                                </div>
                                                    {item.orderSet.map(itm => {
                                                        return(
                                                            <div className="details_items_in_order" key = {itm.id}>
                                                                <p>{itm.shoe.brand.name}</p>
                                                                <p>{itm.shoe.name}</p>
                                                                <p>{itm.size.value}</p>
                                                                <p>{itm.num}</p>
                                                            </div>
                                                        )
                                                    })}
                                            </div>
                                        </div>
                                    )
                                })}
                            </div>
                        </div>
                        <div className="details_personal">Personal information</div>
                        <hr className="hr"/>
                        <div className="form_container">
                            <div className="form_text">Here is your personal information. Please, fill the fields. Your information will be absolutely confidential.</div>
                            <div className="form_box">
                                    <TextField
                                        id="outlined-basic"
                                        label="email"
                                        variant="outlined"
                                        size = "large"
                                        color = "secondary"
                                        defaultValue= {localStorage.getItem('email')}
                                        disabled={true}
                                        sx = {{
                                            width: '40%'
                                        }}
                                    />
                                    <TextField
                                        id="outlined-basic"
                                        label="login"
                                        variant="outlined"
                                        size = "large"
                                        color = "secondary"
                                        defaultValue={localStorage.getItem("username")}
                                        disabled={true}
                                        margin="normal"
                                        sx = {{
                                            width: '40%'
                                        }}
                                    />
                            </div>
                        </div>
                        <div className="details_personal">Password</div>
                        <hr className="hr"/>
                        <div className="form_container">
                            <div className="form_text">Here is your password. You can change it whenever you want. Your safety is very important for us.</div>
                            <div className="form_box">
                                <TextField
                                    id="outlined-basic"
                                    label="current password"
                                    margin = "normal"
                                    variant="outlined"
                                    size = "large"
                                    color = "secondary"
                                    sx = {{
                                        width: '40%'
                                    }}
                                />
                                <TextField
                                    id="outlined-basic"
                                    label="new password"
                                    variant="outlined"
                                    size = "large"
                                    color = "secondary"
                                    margin="normal"
                                    sx = {{
                                        width: '40%'
                                    }}
                                />
                                <TextField
                                    id="outlined-basic"
                                    label="re-enter new password"
                                    variant="outlined"
                                    size = "large"
                                    color = "secondary"
                                    margin="normal"
                                    sx = {{
                                        width: '40%'
                                    }}
                                />
                                <Button variant="contained" color = "secondary" sx = {{
                                    width: '20%',
                                    marginTop: '26px'
                                }}>
                                    save
                                </Button>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default ProfileDetails;