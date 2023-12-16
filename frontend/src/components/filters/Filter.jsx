import React, {useContext, useRef, useState} from 'react';
import './style.css'
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import {Box, Button} from "@mui/material";
import {instance} from "../../utils/axios";
import {ProductContext} from "../../contexts/ProductContext/ProductContext";
const Filter = () => {

    const {setProducts} = useContext(ProductContext)
    const [checked, setChecked] = useState(true)
    const filteredColors = useRef(['red', 'black', 'white', 'green', 'pink', 'beige'])

    const getFilteredColors = (isPressed, colorName) => {
        if (isPressed){
            filteredColors.current.push(colorName)
        }else{
            const index = filteredColors.current.indexOf(colorName)
            filteredColors.current.splice(index, 1)
        }
        console.log(filteredColors)
    }


    const applyFilters = async () => {
        const response = await instance.post('getFilteredProducts', {'colors': filteredColors.current})
        console.log("Send Colors: ", filteredColors)
        console.log("Received shoes", response.data)
        setProducts(response.data)
    }


    return (
        <div className="filters">
            <div className="filters_box">
                <div className="filter_name">TYPE</div>
                <hr className="hr"/>
                <Box sx = {{
                    display: 'flex',
                    flexDirection: 'column',
                    marginLeft: '5%'
                }}>
                    <div className="filter_body">
                        <FormControlLabel control={
                            <Checkbox
                                checked={checked}
                                size="small"
                                color="secondary"
                                onChange={e => {
                                    setChecked(e.target.checked)
                                    console.log(e.target.checked)
                                }}
                            />} label="SNEAKERS"
                        />
                        <FormControlLabel control={
                            <Checkbox
                                defaultChecked
                                size="small"
                                color="secondary"
                            />} label="SLIPPERS" />
                    </div>
                </Box>
                <div className="filter_name">COLOR</div>
                <hr className="hr"/>
                <Box sx = {{
                    display: 'flex',
                    flexDirection: 'column',
                    marginLeft: '5%'
                }}>
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="small"
                            color="secondary"
                            onChange={(e) => getFilteredColors(e.target.checked, 'red')}
                        />} label="RED" />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="small"
                            color="secondary"
                            onChange={(e) => getFilteredColors(e.target.checked, 'black')}
                        />} label="BLACK" />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="small"
                            color="secondary"
                            onChange={(e) => getFilteredColors(e.target.checked, 'white')}
                        />} label="WHITE" />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="small"
                            color="secondary"
                            onChange={(e) => getFilteredColors(e.target.checked, 'green')}
                        />} label="GREEN" />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="small"
                            color="secondary"
                            onChange={(e) => getFilteredColors(e.target.checked, 'pink')}
                        />} label="PINK" />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="small"
                            color="secondary"
                            onChange={(e) => getFilteredColors(e.target.checked, 'beige')}
                        />} label="BEIGE" />
                </Box>
                <div className="filter_name">BRAND</div>
                <hr className="hr"/>
                <Box sx = {{
                    display: 'flex',
                    flexDirection: 'column',
                    marginLeft: '5%'
                }}>
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="small"
                            color="secondary"
                        />} label="ADIDAS" />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="small"
                            color="secondary"
                        />} label="NIKE" />
                    <FormControlLabel control={
                        <Checkbox
                            defaultChecked
                            size="small"
                            color="secondary"
                        />} label="REEBOK"
                    />
                </Box>
                <Button variant="contained" onClick={applyFilters}  color = 'secondary' sx = {{
                    textAlign: 'center',
                    fontWeight: '500',
                    fontSize: '16px',
                    borderRadius: '25px',
                    marginTop: '10%'
                }}>
                    Apply filters
                </Button>
            </div>
        </div>
    );
};

export default Filter;