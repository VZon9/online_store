import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter} from "react-router-dom";
import App from './App';
import ProductProvider from "./contexts/ProductContext/ProductContext";
import CartProvider from "./contexts/CartContext/CartContext";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <BrowserRouter>
        <CartProvider>
            <ProductProvider>
                <React.StrictMode>
                        <App />
                </React.StrictMode>
            </ProductProvider>
        </CartProvider>
    </BrowserRouter>

);

