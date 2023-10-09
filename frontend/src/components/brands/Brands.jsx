import React from 'react';
import './style.css'
import {Box} from "@mui/material";

const Brands = () => {
    return (
        <section className="brands">
            <div className="container">
                <Box sx={{
                    backgroundColor: 'white',
                    borderRadius: 3,
                }}>
                    <ul className="brands_list">
                        <li>ADIDAS</li>
                        <li>NIKE</li>
                        <li>REEBOK</li>
                        <li>NEW BALANCE</li>
                        <li>ASICS</li>
                        <li>CONVERSE</li>
                    </ul>
                </Box>
            </div>
        </section>
    );
};

export default Brands;