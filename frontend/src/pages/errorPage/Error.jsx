import React from 'react';
import './style.css'
import {Link, useParams} from "react-router-dom";
import Footer from "../../components/footer/Footer";

const Error = () => {

    const params = useParams()
    const paramMessage = params.message

    return (
        <div className="error">
            <div className="container">
                <div className="error_text">
                    <p>{paramMessage === 'not-logged-in' ? 'You should be logged in to do this' : 'Something went wrong, we are sorry'}</p>
                    <p>  <Link to="/login" className="err_link"> Log In</Link></p>
                    <p>  <Link to="/home" className="err_link"> Main page</Link></p>
                </div>
            </div>
            <Footer/>
        </div>
    );
};

export default Error;