import React, {createContext, useState, useEffect} from "react";
import {instance} from "../../utils/axios";

export const ProductContext = React.createContext();
 const ProductProvider = ({ children }) => {

     const [products, setProducts] = useState([])
     const [loading, setLoading] = useState(false)
     useEffect(() => {
         const fetchProducts = async () => {
             const response = await instance.get('getProducts')
             const data = await response.data
             console.log("context:", data)
             setProducts(data)
         }
         setLoading(true)
         fetchProducts();
         setLoading(false)
     }, []);

     return <ProductContext.Provider value={{products, setProducts, loading}}>{ children }</ProductContext.Provider>

}
export default ProductProvider