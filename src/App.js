import logo from './logo.svg';
import './App.css';
import HomePage from "./pages/homePage/home";
import PrivateRoute from "./components/utils/router/privateRoute";
import AuthRoot from "./pages/auth/AuthRoot";

function App() {
  return (
    <div className="App">
        <Routes>
            <Route element={<PrivateRoute/>}>
                <Route path="/" element={<HomePage/>}/>
            </Route>
            <Route path="/login" element={<AuthRoot/>}/>
            <Route path="/register" element={<AuthRoot/>}/>
        </Routes>
    </div>
  );
}

export default App;
