import React from 'react';
import "./style.css"
import {Button, TextField} from "@mui/material";
import 'dayjs/locale/ru'
const ProfileDetails = () => {
    return (
        <div>
            <section className="Profile_details">
                <div className="container">
                    <div className="content">
                        <div className="details_title">My details</div>
                        <div className="details_personal">Personal information</div>
                        <hr className="hr"/>
                        <div className="form_container">
                            <div className="form_text">Here is your personal information. Please, fill the fields. Your information will be absolutely confidential.</div>
                            <div className="form_box">
                                    <TextField
                                        id="outlined-basic"
                                        label="email"
                                        variant="outlined"
                                        size = "large"
                                        color = "secondary"
                                        defaultValue= {localStorage.getItem('email')}
                                        disabled={true}
                                        sx = {{
                                            width: '40%'
                                        }}
                                    />
                                    <TextField
                                        id="outlined-basic"
                                        label="login"
                                        variant="outlined"
                                        size = "large"
                                        color = "secondary"
                                        defaultValue={localStorage.getItem("username")}
                                        disabled={true}
                                        margin="normal"
                                        sx = {{
                                            width: '40%'
                                        }}
                                    />
                            </div>
                        </div>
                        <div className="details_personal">Password</div>
                        <hr className="hr"/>
                        <div className="form_container">
                            <div className="form_text">Here is your password. You can change it whenever you want. Your safety is very important for us.</div>
                            <div className="form_box">
                                <TextField
                                    id="outlined-basic"
                                    label="current password"
                                    margin = "normal"
                                    variant="outlined"
                                    size = "large"
                                    color = "secondary"
                                    sx = {{
                                        width: '40%'
                                    }}
                                />
                                <TextField
                                    id="outlined-basic"
                                    label="new password"
                                    variant="outlined"
                                    size = "large"
                                    color = "secondary"
                                    margin="normal"
                                    sx = {{
                                        width: '40%'
                                    }}
                                />
                                <TextField
                                    id="outlined-basic"
                                    label="re-enter new password"
                                    variant="outlined"
                                    size = "large"
                                    color = "secondary"
                                    margin="normal"
                                    sx = {{
                                        width: '40%'
                                    }}
                                />
                                <Button variant="contained" color = "secondary" sx = {{
                                    width: '20%',
                                    marginTop: '26px'
                                }}>
                                    save
                                </Button>
                            </div>
                        </div>
                        <div className="details_personal">My orders</div>
                        <hr className="hr"/>
                        <div className="form_container">
                            <div className="form_text">Here is your history of orders. Thanks for choosen us.</div>
                        </div>
                        <div className="form_box">
                            <div className="history_item">
                            </div>

                        </div>
                    </div>

                </div>
            </section>
        </div>
    );
};

export default ProfileDetails;