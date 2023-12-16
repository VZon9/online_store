import React from 'react';
import img1 from "../../../img/miniImgs/yeezy350v2_1.jpg";
import './style.css'
const LargeImgSwiper = (props) => {
    const {img} = props
    const image = 'http://26.134.246.98:8080/img/' + img + '_main.png'
    return (
        <div>
            <div className="large_img_swiper">
                <div className="shoe_large_box">
                    <img src={image} alt = "350v2" className="shoe_large_img" id = "img1"/>
                </div>
                <div className="shoe_large_box">
                    <img src={image} alt = "350v2" className="shoe_large_img" id = "img2"/>
                </div>
                <div className="shoe_large_box">
                    <img src={image} alt = "350v2" className="shoe_large_img" id = "img3"/>
                </div>
                <div className="shoe_large_box">
                    <img src={image} alt = "350v2" className="shoe_large_img" id = "img4"/>
                </div>
            </div>
        </div>
    );
};

export default LargeImgSwiper;