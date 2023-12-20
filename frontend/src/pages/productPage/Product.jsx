import React from 'react';
import Header from "../../components/header/Header";
import Footer from "../../components/footer/Footer";
import ProductOverlook from "../../components/productOverlook/ProductOverlook";

const Product = () => {
    return (
        <div>
            <Header />
            <ProductOverlook/>
            <Footer/>
        </div>
    );
};

export default Product;