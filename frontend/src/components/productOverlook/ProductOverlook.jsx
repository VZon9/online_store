import React, {useEffect, useState} from 'react';
import './style.css'
import MiniImgSwiper from "./miniImgSwiper/MiniImgSwiper";
import LargeImgSwiper from "./largeImgSwiper/LargeImgSwiper";
import ProductInfo from "./productInfo/ProductInfo";
import {useParams} from "react-router-dom";
import {instance} from "../../utils/axios";
const ProductOverlook = () => {

    const params = useParams()
    const prodId = params.id
    const [product, setProduct] = useState({})
    useEffect(() => {
        console.log("prodId", prodId)
        const fetchProduct =   async (prodId) => {
            const response =    await instance.post("getProduct", {'id':prodId})
            const data = await response.data;
            setProduct(data)
            console.log("context:", product)
        }
        fetchProduct(prodId);
    },[prodId]);


    // console.log("product", product)

    return (
        <section className="product_overlook">
            <div className="container">
                <div className="product_overlook_box">
                    <MiniImgSwiper/>
                    <LargeImgSwiper/>
                    <ProductInfo/>
                </div>
            </div>
        </section>
    );
};

export default ProductOverlook;