import React from 'react';

import './style.css'
import Card from "../card/Card";

const Popular = () => {
    return (
    <section className="popular">
        <div className="container">
            <div className="popular_header">
                POPULAR PAIRS
            </div>
            <div className="popular_cards">
                <Card/>
                <Card/>
                <Card/>
            </div>
        </div>
    </section>
    );
};

export default Popular;