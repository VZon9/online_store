import React from 'react';
import Header from "../../components/header/Header";
import Promo from "../../components/promo/Promo";
import Brands from "../../components/brands/Brands";
import Popular from "../../components/popular/Popular";
import Footer from "../../components/footer/Footer";

const HomePage = () => {
    return (
        <div>
            <Header/>
            <Promo/>
            <Brands/>
            <Popular/>
            <Footer/>
        </div>
    );
};

export default HomePage;