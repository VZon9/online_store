import React, {createContext, useState, useEffect} from "react";
import axios from "axios";
import {instance} from "../../utils/axios";

export const ProductContext = React.createContext();
export let defaultProducts ={}
 const ProductProvider = ({ children }) => {

     const [products, setProducts] = useState([])

     useEffect(() => {
         const fetchProducts = async () => {
             //const response = await axios.get('https://fakestoreapi.com/products')
             const response = await instance.get('getProducts')
             const data = await response.data
             defaultProducts = data
             console.log("context:", data)
             setProducts(data)
         }
         fetchProducts();
     }, []);

     return <ProductContext.Provider value={{products, setProducts}}>{ children }</ProductContext.Provider>

}
export default ProductProvider