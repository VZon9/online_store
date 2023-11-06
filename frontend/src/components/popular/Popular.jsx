import React from 'react';

import './style.css'
import PopularCard from "../popularCard/PopularCard";

const Popular = () => {
    return (
    <section className="popular">
        <div className="container">
            <div className="popular_header">
                POPULAR PAIRS
            </div>
            <div className="popular_cards">
                <PopularCard title = {'TITLE'} description = {'DESCRIPTION'} price = {'300$'}/>
                <PopularCard title = {'TITLE'} description = {'DESCRIPTION'} price = {'300$'}/>
                <PopularCard title = {'TITLE'} description = {'DESCRIPTION'} price = {'300$'}/>
            </div>
        </div>
    </section>
    );
};

export default Popular;