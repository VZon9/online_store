import './App.css';
import HomePage from "./pages/homePage/Home";
import PrivateRoute from "./components/utils/router/privateRoute";
import AuthRoot from "./pages/auth/AuthRoot";
import {Route, Routes} from "react-router-dom";

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
        </Routes>
    </div>
  );
}

export default App;
