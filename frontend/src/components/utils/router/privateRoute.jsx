import React from 'react';
import {Navigate, Outlet} from "react-router-dom";

const PrivateRoute = () => {
    //Temporary variable
    const hasPermit = true;
    return (
        hasPermit ? <Outlet/> : <Navigate to={"/login"}/>
    );
};

export default PrivateRoute;