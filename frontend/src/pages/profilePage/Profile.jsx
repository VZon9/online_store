import React from 'react';
import Header from "../../components/header/Header";
import {email} from "../auth/AuthRoot";
import Footer from "../../components/footer/Footer";
import ProfileDetails from "../../components/profile_details/ProfileDetails";
const Profile = () => {
    return (
        <div>
            <Header />
            <ProfileDetails/>
            <Footer />
        </div>
    );
};

export default Profile;