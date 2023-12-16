import React, {useContext, useState} from 'react';
import './style.css'
import {Button, Typography} from "@mui/material";
import {CartContext} from "../../../contexts/CartContext/CartContext";
import MuiAlert from "@mui/material/Alert";
import Snackbar from "@mui/material/Snackbar";

const Alert = React.forwardRef(function Alert(props, ref) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});
const ProductInfo = (props) => {
    const {addToCart, sizeErrMess, openSnackbar, handleCloseSnackbar, addToCartMes} = useContext(CartContext)
    const {brand, model, id, sizeList, desc} = props
    const [selectedSize, setSelectedSize] = useState(0);
    const handleSelectSize= (e, index) => {
        setSelectedSize(index)
        console.log(selectedSize)
    }

    return (
        <section className="product_info">
            <div className="product_brandname">{brand}</div>
            <div className="product_modelname">{model}</div>
            <div className="sizes_text">Available sizes</div>
            <div className="size_list">
                {sizeList?.map(size => {
                    return(
                        <div
                            className={ size.existingNum === 0 ? "size_not_available" : selectedSize === size.id ? "size_box_sel" : "size_box_def" }
                            key = {size.id}
                            onClick={(e) => handleSelectSize(e, size.id)}
                        >

                            {size.size.value}

                        </div>
                    )
                })}
            </div>
            <div>
                <Button variant="contained"  size="large" onClick={() => {addToCart(id, selectedSize)}} sx ={{
                    textAlign: 'center',
                    fontWeight: '500',
                    fontSize: '24px',
                    background: '#ef5350',
                    lineHeight: '1.5',
                    letterSpacing: '-0.03em',
                    padding: '17px 22px 17px',
                    borderRadius: '25px',
                    marginTop: '20%'
            }}>
                    ADD TO CART
                </Button>
            </div>
            <div>
                <Typography variant="body2" component="p" textAlign = 'center' color = 'red'
                            sx = {{
                                marginTop: '15px',
                                fontSize: '18px',
                                fontWeight: '500'
                            }} >
                    {sizeErrMess}
                </Typography>
            </div>
            <div className='product_desc'>
                {desc?.split('\n').map(s =>{
                    return (
                        <div>
                            <p className="desc_str">
                                {s}
                            </p>
                        </div>
                    )
                })}
            </div>
            <Snackbar open={openSnackbar} autoHideDuration={3000} onClose={handleCloseSnackbar}>
                <Alert onClose={handleCloseSnackbar} severity="success" sx={{ width: '100%' }}>
                    {addToCartMes}
                </Alert>
            </Snackbar>
        </section>
    );
};

export default ProductInfo;