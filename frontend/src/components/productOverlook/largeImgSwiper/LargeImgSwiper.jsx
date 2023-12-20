import React from 'react';
import './style.css'
import Carousel from './../carousel/Carousel'
const LargeImgSwiper = (props) => {
    const {img, desc} = props
    const image = 'http://26.134.246.98:8080/img/' + img + '_main.png'
    return (
        <div className="slider_text">
            <Carousel>
                <img src={image} alt = "img" className="item item1"/>
                <img src={image} alt = "img" className="item item2" />
                <img src={image} alt = "img" className="item item3"/>
            </Carousel>
            <div className="description">
                {desc?.split('\n').map(s =>{
                    return (
                        <div>
                            <p className="desc_str">
                                {s}
                            </p>
                        </div>
                    )
                })}
            </div>
        </div>
        // <div>
        //     <div className="large_img_swiper">
        //         <div className="shoe_large_box">
        //             <img src={image} alt = "350v2" className="shoe_large_img" id = "img1"/>
        //         </div>
        //         <div className="shoe_large_box">
        //             <img src={image} alt = "350v2" className="shoe_large_img" id = "img2"/>
        //         </div>
        //         <div className="shoe_large_box">
        //             <img src={image} alt = "350v2" className="shoe_large_img" id = "img3"/>
        //         </div>
        //         <div className="shoe_large_box">
        //             <img src={image} alt = "350v2" className="shoe_large_img" id = "img4"/>
        //         </div>
        //     </div>
        // </div>
    );
};

export default LargeImgSwiper;