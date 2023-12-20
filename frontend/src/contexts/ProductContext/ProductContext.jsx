import React, {useState, useEffect} from "react";
import {instance} from "../../utils/axios";
import {useNavigate} from "react-router-dom";

export const ProductContext = React.createContext();
 const ProductProvider = ({ children }) => {

     const navigate = useNavigate()
     const [products, setProducts] = useState([])


     useEffect(() => {
         const fetchProducts = async () => {
             try {
                 const response = await instance.get('getProducts')
                 const data = await response.data
                 console.log("context:", data)
                 setProducts(data)
             }catch (error) {
                 navigate("/error" + "fail-fetch-products")
             }
         }
         fetchProducts();
     }, []);

     return <ProductContext.Provider value={{products, setProducts}}>{ children }</ProductContext.Provider>

}
export default ProductProvider