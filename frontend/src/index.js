import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter} from "react-router-dom";
import App from './App';
import ProductProvider from "./contexts/ProductContext/ProductContext";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <ProductProvider>
        <React.StrictMode>
            <BrowserRouter>
                <App />
            </BrowserRouter>
        </React.StrictMode>
    </ProductProvider>

);

