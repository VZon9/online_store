import React, {useEffect, useRef, useState} from 'react';
import './style.css'
import MiniImgSwiper from "./miniImgSwiper/MiniImgSwiper";
import LargeImgSwiper from "./largeImgSwiper/LargeImgSwiper";
import ProductInfo from "./productInfo/ProductInfo";
import {useParams} from "react-router-dom";
import {instance} from "../../utils/axios";
import {Backdrop, CircularProgress} from "@mui/material";

const ProductOverlook = () => {

    const params = useParams()
    const prodId = params.id
    const [product, setProduct] = useState({})
    const [isLoading, setIsLoading] = useState(false)
    useEffect(() => {
        const fetchProduct =   async (prodId) => {
            const response =    await instance.post("getProduct", {'id':prodId})
            setProduct( await response.data)
        }
        setIsLoading(true)
        fetchProduct(prodId);
        setIsLoading(false)
    },[prodId]);

    return (
        <section className="product_overlook">
            <Backdrop
                sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
                open={isLoading}
            >
                <CircularProgress color="secondary" />
            </Backdrop>
            <div className="container">
                <div className="product_overlook_box">
                    <MiniImgSwiper img = {product.imagePattern}/>
                    <LargeImgSwiper img = {product.imagePattern}/>
                    <ProductInfo brand={product.brand?.name} model={product.name} id={prodId} sizeList={product.sizeList} desc = {product.description}/>
                </div>
            </div>
        </section>
    );
};

export default ProductOverlook;