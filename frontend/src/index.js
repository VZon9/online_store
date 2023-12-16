import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter} from "react-router-dom";
import App from './App';
import ProductProvider from "./contexts/ProductContext/ProductContext";
import CartProvider from "./contexts/CartContext/CartContext";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <CartProvider>
        <ProductProvider>
            <React.StrictMode>
                <BrowserRouter>
                    <App />
                </BrowserRouter>
            </React.StrictMode>
        </ProductProvider>
    </CartProvider>

);

