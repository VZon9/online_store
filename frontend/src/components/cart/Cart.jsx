import React, {useContext, useRef, useState} from 'react';
import './style.css'
import {CartContext} from "../../contexts/CartContext/CartContext";
import CartItem from "../cartItem/CartItem";
import CartEmptyItem from "../cartEmptyItem/CartEmptyItem";
import {Box, Button, Typography} from "@mui/material";
import {instance} from "../../utils/axios";
import {ACCESS_TOKEN_KEY} from "../../pages/auth/AuthRoot";
import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';

const Alert = React.forwardRef(function Alert(props, ref) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

const Cart = () => {
    const {cart, refreshBasket} = useContext(CartContext)
    const [totalPrice, setTotalPrice] = useState(0)
    const [totalCnt, setTotalCnt] = useState(0)
    const selectedForOrder = useRef([])
    const [openSnackbar, setOpenSnackbar] = useState(false)
    const [makeOrderMes, setMakeOrderMes] = useState('')
    const makeOrder = async () => {
        try {
            const response = await instance.post('makeOrder', {'basketsId': selectedForOrder.current, 'userId': localStorage.getItem('userId')}, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem(ACCESS_TOKEN_KEY)}`
                }
            })
            console.log(await response.data.message)
            setMakeOrderMes(await response.data.message)
            await refreshBasket()
            setOpenSnackbar(true)
            selectedForOrder.current = []
            setTotalCnt(0)
            setTotalPrice(0)
        } catch (error){
            console.log(error)
            setMakeOrderMes(await error.message)
        }

    }

    const handleCloseSnackbar = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }
        setOpenSnackbar(false);
    }

        return (
            <section className="cart">
                <div className="container">
                    <div className="cart_content">
                        <div className="cart_shoelist">
                            {cart.map(item => {
                                return(
                                    <CartItem key = {item.id}
                                              brand = {item.shoe.brand.name}
                                              model = {item.shoe.name}
                                              price = {item.shoe.price}
                                              id = {item.id}
                                              size = {item.size.value}
                                              quantity = {item.num}
                                              img = {item.shoe.imagePattern}
                                              type = {item.shoe.type.name}
                                              totalPrice = {totalPrice}
                                              setTotalPrice={setTotalPrice}
                                              totalCnt = {totalCnt}
                                              setTotalCnt = {setTotalCnt}
                                              selectedForOrder ={selectedForOrder}
                                    />
                                )
                            })}
                        </div>
                        <div className="cart_makeorder">
                                <Button variant="contained" onClick={makeOrder} sx = {{
                                    marginTop: 0,
                                    textAlign: 'center',
                                    borderRadius: '25px',
                                    width: '100%',
                                    background: '#ef5350'
                                }}>
                                    <Typography fontWeight='900' fontSize='16px' padding='10px 20px'> Make an order </Typography>
                                </Button>

                            <Snackbar open={openSnackbar} autoHideDuration={3000} onClose={handleCloseSnackbar}>
                                <Alert onClose={handleCloseSnackbar} severity="success" sx={{ width: '100%' }}>
                                    {makeOrderMes}
                                </Alert>
                            </Snackbar>

                            <Typography fontWeight='500' fontSize='18px' padding='10px 20px' fontFamily='Poppins'> Information </Typography>
                            <Box sx ={{
                                display: 'flex',
                                flexDirection: 'column',
                                paddingLeft: '5%'
                            }}>
                                <Box sx = {{
                                    display: 'flex',
                                    justifyContent: 'space-between'
                                }}>
                                    <Typography fontWeight='500' fontSize='16px' padding='10px 20px' fontFamily='Poppins'> Products </Typography>
                                    <Typography fontWeight='500' fontSize='16px' padding='10px 20px' fontFamily='Poppins'> {totalCnt} </Typography>
                                </Box>
                                <Box sx = {{
                                    display: 'flex',
                                    justifyContent: 'space-between'
                                }}>
                                    <Typography fontWeight='500' fontSize='16px' padding='10px 20px' fontFamily='Poppins'> Total price </Typography>
                                    <Typography fontWeight='500' fontSize='16px' padding='10px 20px' fontFamily='Poppins'> {totalPrice} </Typography>
                                </Box>
                            </Box>
                        </div>
                    </div>
                </div>
            </section>
        );
    // }
};

export default Cart;