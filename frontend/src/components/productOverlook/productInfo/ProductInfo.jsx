import React from 'react';
import './style.css'
import {Button} from "@mui/material";
import {Link} from "react-router-dom";
const ProductInfo = (props) => {
    const {brand, model} = props
    return (
        <section className="product_info">
            <div className="product_brandname">{brand}</div>
            <div className="product_modelname">{model}</div>
            <div className="sizes_text">Available sizes</div>
            <div className="sizes_list">

            </div>
            <div>
                <Button variant="contained"  size="large" sx ={{
                    textAlign: 'center',
                    fontWeight: '500',
                    fontSize: '24px',
                    background: '#ef5350',
                    lineHeight: '1.5',
                    letterSpacing: '-0.03em',
                    padding: '17px 22px 17px',
                    borderRadius: '25px'
            }}>
                    ADD TO CART
                </Button>
            </div>

        </section>
    );
};

export default ProductInfo;