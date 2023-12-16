import React, {createContext, useEffect, useState} from 'react';
import {instance} from "../../utils/axios";
import {ACCESS_TOKEN_KEY} from "../../pages/auth/AuthRoot";

export const CartContext = createContext()
const CartProvider = ({ children }) => {
    const [sizeErrMess, setSizeErrMess] = useState('')
    const [cart, setCart] = useState([])
    const [openSnackbar, setOpenSnackbar] = useState(false)
    const [addToCartMes, setAddToCartMes] = useState('')
    useEffect(() => {
        const fetchBasket = async () => {
            const response = await instance.post('getBasket', {'userId': localStorage.getItem('userId')}, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem(ACCESS_TOKEN_KEY)}`
                }
            })
            const data = await response.data
            console.log('cartContext:', data)
            setCart(data)
        }
        fetchBasket();
    }, []);


    const refreshBasket = async () => {
            const response = await instance.post('getBasket', {'userId': localStorage.getItem('userId')}, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem(ACCESS_TOKEN_KEY)}`
                }
            })
            const data = await response.data
            console.log('refreshCart:', data)
            setCart(data)
    }

    const addToCart = async (id, selectedSize) => {
        console.log(`added ${id} to cart`)
        try {
            const response = await instance.post('basketAdd', {'shoeId':id, 'userId': localStorage.getItem('userId'), 'sizeId': selectedSize}, {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem(ACCESS_TOKEN_KEY)}`
                    }
                }
            )
            setSizeErrMess('')
            setAddToCartMes(await response.data.message)
            setOpenSnackbar(true)
            await refreshBasket()
        }catch (error){
            setSizeErrMess(error.response.data.message)
        }

    }


    const rmFromCart = async (id) => {
        const response = await instance.post('removeFromBasket', {'basketId':id, 'userId': localStorage.getItem('userId')}, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem(ACCESS_TOKEN_KEY)}`
                }
            }
        )
        console.log(await response.data.message)
        console.log(`removed ${id} from cart`)
        await refreshBasket()
    }

    const handleCloseSnackbar = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }
        setOpenSnackbar(false);
    }

    return (
        <CartContext.Provider value={{addToCart, cart, rmFromCart, sizeErrMess, refreshBasket, openSnackbar, handleCloseSnackbar, addToCartMes}}>
            {children}
        </CartContext.Provider>
    );
};

export default CartProvider;