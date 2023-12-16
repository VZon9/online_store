import React, {useContext, useState} from 'react';
import './style.css'
import Card from "../card/Card";
import {ProductContext} from "../../contexts/ProductContext/ProductContext";
import {useNavigate} from "react-router-dom";
import {instance} from "../../utils/axios";
import {Backdrop, Button, CircularProgress} from "@mui/material";
import Filter from '../filters/Filter'
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import FormControl from '@mui/material/FormControl';

const ProductsList = () => {
    const navigate = useNavigate()
    const [sort, setSort] = useState('')
    const {products, loading} = useContext(ProductContext)
    const {setProducts} = useContext(ProductContext)
    console.log("productsListContext", products)

    const handleChangeSort = (event) => {
        setSort(event.target.value);
    };

    const getProduct = async (id) => {
        await navigate("/products/" + id)
    }

    const getSorted = async (type, direction) => {
        if (type === 'price'){
            if (direction === 'up') {
                const response = await instance.post('sort', {'sortType': 'price', 'direction': 'up'})
                setProducts(response.data)
            }else {
                const response = await instance.post('sort', {'sortType': 'price', 'direction': 'down'})
                setProducts(response.data)
            }
        }else{
            if (direction === 'up') {
                const response = await instance.post('sort', {'sortType': 'default', 'direction':'up'})
                setProducts(response.data)
            } else {
                const response = await instance.post('sort', {'sortType': 'default', 'direction':'down'})
                setProducts(response.data)
            }

        }

    }

    return (
        <section className="products_list">
            <Backdrop
                sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
                open={loading}
            >
                <CircularProgress color="inherit" />
            </Backdrop>

            <div className="container">
                <div className="sort">
                    <FormControl sx={{
                        m: 1,
                        minWidth: 120
                    }}>
                        <InputLabel id="demo-simple-select-autowidth-label">Sorting</InputLabel>
                        <Select
                            labelId="demo-simple-select-autowidth-label"
                            id="demo-simple-select-autowidth"
                            value = {sort}
                            onChange={handleChangeSort}
                            autoWidth
                            label="Sorting"
                            color='secondary'
                            sx = {{
                                borderRadius: '12px'
                            }}
                        >
                            <MenuItem value={'priceUp'} onClick={() => getSorted('price', 'up')}>Sort by descending price</MenuItem>
                            <MenuItem value={'priceDown'} onClick={() => getSorted('price', 'down')}>Sort by ascending price</MenuItem>
                            <MenuItem value={'popularityUp'} onClick={() => getSorted('default', 'up')}> Sort by descending popularity</MenuItem>
                            <MenuItem value={'popularityDown'} onClick={() => getSorted('default', 'down')}> Sort by ascending popularity</MenuItem>
                        </Select>
                    </FormControl>
                </div>
                <div className="products_list_content">
                    <Filter/>
                    <div className="products_list_wrapper">
                        {products.map(product => {
                            return(
                                <div className="product_card" onClick={() => getProduct(product.id)} key = {product.id}>
                                    <Card key = {product.id} brand = {product.brand.name} model = {product.name} color = {product.color}  price = {product.price} img = {product.imagePattern}/>
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