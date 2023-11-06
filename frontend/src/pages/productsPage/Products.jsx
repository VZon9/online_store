import React from 'react';
import Header from "../../components/header/Header";
import Footer from "../../components/footer/Footer";
import ProductsList from "../../components/productsList/ProductsList";


const Products = () => {
    return (
        <div>
            <Header/>
            <ProductsList/>
            <Footer />
        </div>
    );
};

export default Products;