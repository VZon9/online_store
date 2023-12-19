import React, {useEffect, useState} from 'react';
import "./style.css"
import {Button, TextField} from "@mui/material";
import 'dayjs/locale/ru'
import {instance} from "../../utils/axios";
import {ACCESS_TOKEN_KEY} from "../../pages/auth/AuthRoot";
import {useNavigate} from "react-router-dom";
import ProductInOrderCard from "../produtctInOrderCard/ProductInOrderCard";
export let price = 0
const ProfileDetails = () => {

    const navigate = useNavigate()
    const [orders, setOrders] = useState([])

    useEffect(() => {
        const fetchOrders = async () => {
            try {
                const response = await instance.post('getOrders', {'userId': localStorage.getItem('userId')}, {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem(ACCESS_TOKEN_KEY)}`
                    }
                })
                const data = await response.data
                console.log('orders:', data)
                setOrders(data)
            } catch (error) {
                navigate("/error" + "fail-fetch-orders")
            }
        }
        fetchOrders();
    }, []);

    const logout =  () => {
        localStorage.setItem('isAuth', 'false')
        navigate("/home")
    }

    return (
        <div>
            <section className="Profile_details">
                <div className="container">
                    <div className="content">
                        <div className="details_title">My details</div>
                        <div className="details_personal">My orders</div>
                        <hr className="profile_hr"/>
                        <div className="form_box">
                            <div className="orders_history">
                                {orders.map(item => {
                                    {price = 0}
                                    return(
                                        <div className="orders_history_card" key = {item.id}>
                                            <div className="orders_history_card_wrapper">
                                                <div className="order_text">
                                                    <div className="item_info_date">
                                                        <h5>Order from: {item.date} </h5>
                                                    </div>
                                                    <div className="item_info_status">
                                                        {item.status}
                                                    </div>
                                                </div>
                                                <div className="item_info_number">
                                                    Order number: {item.id}
                                                </div>
                                                <hr className="divider"/>
                                                    {item.orderSet.map(itm => {
                                                        return(
                                                            <div className="order_content" key = {itm.id}>
                                                                <ProductInOrderCard
                                                                    brand = {itm.shoe.brand.name}
                                                                    model = {itm.shoe.name}
                                                                    size = {itm.size.value}
                                                                    img = {itm.shoe.imagePattern}
                                                                    productPrice = {itm.shoe.price}
                                                                    />
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
                        <div className="details_personal">Logout</div>
                        <hr className="hr"/>
                        <div className="form_container">
                            <div className="form_text">Here is the logout button. You can press it and then you will use our site as anonymous.</div>
                            <div className="form_box">
                                <Button variant="contained" size = "large" color = "secondary" onClick={logout} sx = {{
                                    width: '20%',
                                    marginTop: '26px',
                                    marginBottom: '40px'
                                }}>
                                    Logout
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