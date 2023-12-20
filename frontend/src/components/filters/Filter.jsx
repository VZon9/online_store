import React, {useContext, useRef, useState} from 'react';
import './style.css'
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import {Box, Button} from "@mui/material";
import {instance} from "../../utils/axios";
import {ProductContext} from "../../contexts/ProductContext/ProductContext";
const Filter = () => {

    const {setProducts} = useContext(ProductContext)
    const filteredColors = useRef(['red', 'black', 'white', 'green', 'pink', 'beige'])
    const filteredBrands = useRef(['ADIDAS', 'NIKE', 'REEBOK', 'ASICS', 'NEW BALANCE'])
    const filteredType = useRef(['sneaker','slipper'])

    const getFilteredColors = (isPressed, colorName) => {
        if (isPressed){
            filteredColors.current.push(colorName)
        }else{
            const index = filteredColors.current.indexOf(colorName)
            filteredColors.current.splice(index, 1)
        }
    }

    const getFilteredBrands = (isPressed, brandName) => {
        if (isPressed){
            filteredBrands.current.push(brandName)
        }else{
            const index = filteredBrands.current.indexOf(brandName)
            filteredBrands.current.splice(index, 1)
        }
    }

    const getFilteredTypes = (isPressed, typeName) => {
        if (isPressed){
            filteredType.current.push(typeName)
        }else{
            const index = filteredType.current.indexOf(typeName)
            filteredType.current.splice(index, 1)
        }
    }


    const applyFilters = async () => {
        const response = await instance.post('getFilteredProducts', {'colors': filteredColors.current, 'brands': filteredBrands.current,
                                                                                                    'types': filteredType.current})
        setProducts(response.data)
    }

    return (
        <div className="filters">
            <div className="filters_wrapper">
                <div className="filter_name">Type</div>
                <Box sx = {{
                    display: 'flex',
                    flexDirection: 'column',
                    marginLeft: '5%',
                }}>
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="medium"
                            color="secondary"
                            onChange={(e) => getFilteredTypes(e.target.checked, 'sneaker')}
                        />} label="Sneakers"
                    />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="medium"
                            color="secondary"
                            onChange={(e) => getFilteredTypes(e.target.checked, 'slipper')}
                        />} label="Slippers" />
                </Box>
                <hr className="filter_hr"/>
                <div className="filter_name">Color</div>
                <Box sx = {{
                    display: 'flex',
                    flexDirection: 'column',
                    marginLeft: '5%'
                }}>
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="medium"
                            color="secondary"
                            onChange={(e) => getFilteredColors(e.target.checked, 'red')}
                        />} label="Red" />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="medium"
                            color="secondary"
                            onChange={(e) => getFilteredColors(e.target.checked, 'black')}
                        />} label="Black" />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="medium"
                            color="secondary"
                            onChange={(e) => getFilteredColors(e.target.checked, 'white')}
                        />} label="White" />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="medium"
                            color="secondary"
                            onChange={(e) => getFilteredColors(e.target.checked, 'green')}
                        />} label="Green" />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="medium"
                            color="secondary"
                            onChange={(e) => getFilteredColors(e.target.checked, 'pink')}
                        />} label="Pink" />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="medium"
                            color="secondary"
                            onChange={(e) => getFilteredColors(e.target.checked, 'beige')}
                        />} label="Beige" />
                </Box>
                <hr className="filter_hr"/>
                <div className="filter_name">Brand</div>
                <Box sx = {{
                    display: 'flex',
                    flexDirection: 'column',
                    marginLeft: '5%'
                }}>
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="medium"
                            color="secondary"
                            onChange={(e) => getFilteredBrands(e.target.checked, 'ADIDAS')}
                        />} label="Adidas" />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="medium"
                            color="secondary"
                            onChange={(e) => getFilteredBrands(e.target.checked, 'NIKE')}
                        />} label="Nike" />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="medium"
                            color="secondary"
                            onChange={(e) => getFilteredBrands(e.target.checked, 'REEBOK')}
                        />} label="Reebok" />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="medium"
                            color="secondary"
                            onChange={(e) => getFilteredBrands(e.target.checked, 'NEW BALANCE')}
                        />} label="New balance" />
                </Box>
                <Button variant="contained" onClick={applyFilters}  color = 'secondary' sx = {{
                    textAlign: 'center',
                    fontWeight: '500',
                    fontSize: '22px',
                    lineHeight: '1.5',
                    letterSpacing: '-0.03em',
                    padding: '17px 22px 17px',
                    borderRadius: '14px'
                }}>
                    Apply filters
                </Button>
            </div>
        </div>
    );
};

export default Filter;