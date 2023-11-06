import React, {useContext, useState} from 'react';
import './style.css'
import Card from "../card/Card";
import {ProductContext} from "../../contexts/ProductContext/ProductContext";
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import {defaultProducts} from "../../contexts/ProductContext/ProductContext";
import {Link, useNavigate} from "react-router-dom";
import {instance} from "../../utils/axios";
import Product from "../../pages/productPage/Product";
import {Home} from "@mui/icons-material";
import {Button} from "@mui/material";

const ProductsList = () => {
    const navigate = useNavigate()

    const [isTrue, setIsTrue] = useState(true)

    const {products} = useContext(ProductContext)
    const {setProducts} = useContext(ProductContext)
    console.log("productsListContext", products)
    function getFiltered(isTrue, filterName){
        if (isTrue) {
            return setProducts([...products].filter(item =>
                    item.type.name === filterName))
        }else
            setProducts(defaultProducts)
                return defaultProducts
    }


    const getProduct = async (id) => {
        navigate("/products/" + id)
    }

    return (
        <section className="products_list">
            <div className="container">
                <div className="products_list_content">
                    <div className="filters">
                        <div className="filters_box">
                            <div className="filter_name">TYPE</div>
                            <hr className="hr"/>
                            <div className="filter_body">
                                <FormControlLabel control={
                                    <Checkbox
                                        defaultChecked
                                        size="small"
                                        color="secondary"
                                        onChange={e => {
                                            getFiltered(isTrue, "slipper")
                                            setIsTrue(e.target.checked)
                                        }}
                                />} label="Sneackers"
                                    />
                                <FormControlLabel control={
                                    <Checkbox
                                        defaultChecked
                                        size="small"
                                        color="secondary"
                                    />}
                                    label="Slippers" />
                            </div>
                            <div className="filter_name">COLOR</div>
                            <hr className="hr"/>
                            <FormControlLabel control={
                                <Checkbox
                                    defaultChecked
                                    size="small"
                                />} label="RED" />
                            <FormControlLabel control={
                                <Checkbox
                                    defaultChecked
                                    size="small"
                                />} label="BLACK" />
                            <FormControlLabel control={
                                <Checkbox
                                    defaultChecked
                                    size="small"
                                />} label="WHITE" />
                            <FormControlLabel control={
                                <Checkbox
                                    defaultChecked
                                    size="small"
                                />} label="GREEN" />
                            <div className="filter_name">BRAND</div>
                            <hr className="hr"/>
                            <FormControlLabel control={
                                <Checkbox
                                    defaultChecked
                                    size="small"
                                />} label="ADIDAS" />
                            <FormControlLabel control={
                                <Checkbox
                                    defaultChecked
                                    size="small"
                                />} label="NIKE" />
                            <FormControlLabel control={
                                <Checkbox
                                    defaultChecked
                                    size="small"
                                />} label="REEBOK" />
                        </div>
                    </div>
                    <div className="products_list_wrapper">
                        {products.map(product => {
                            return(
                                <div className="product_card" onClick={() => getProduct(product.id)}>
                                    <Card key = {product.id} brand = {product.brand.name} model = {product.name} color = {product.color} />
                                    {/*<Card key = {product.id} title = {product.title} rating = {product.rating.rate}/>*/}
                                </div>
                            )
                        })}
                </div>
                </div>
            </div>
        </section>
    );
};

export default ProductsList;