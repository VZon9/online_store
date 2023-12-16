import './App.css';
import HomePage from "./pages/homePage/Home";
import PrivateRoute from "./components/utils/router/privateRoute";
import AuthRoot from "./pages/auth/AuthRoot";
import {Route, Routes} from "react-router-dom";
import Profile from "./pages/profilePage/Profile";
import Products from "./pages/productsPage/Products";
import Product from "./pages/productPage/Product";
import Basket from "./pages/basketPage/Basket";

function App() {
  return (
    <div className="App">
        <Routes>
            <Route element={<PrivateRoute/>}>
                <Route path="/home" element={<HomePage/>}/>
            </Route>
            <Route path="/login" element={<AuthRoot/>}/>
            <Route path="/register" element={<AuthRoot/>}/>
            <Route path="/test" element={<AuthRoot/>}/>
            <Route path="/profile" element={<Profile/>}/>
            <Route path="/cart" element={<Basket/>}/>
            <Route path="/products" element={<Products/>}/>
            <Route path="/products/:id" element={<Product/>}/>
        </Routes>
    </div>
  );
}

export default App;
