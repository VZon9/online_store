import React, {useState} from 'react';
import img1 from './../../../img/miniImgs/yeezy350v2_1.jpg'
import './style.css'
const MiniImgSwiper = () => {
    const [selectedIndex, setSelectedIndex] = useState(1);

    const handleClick = (e, index) => {
        setSelectedIndex(index)
        console.log(selectedIndex)
    }

    return (
        <div>
            <div className="mini_img_swiper">
                <a href = "#img1" onClick={(e) => handleClick(e, 1)} className={selectedIndex === 1 ? "mini_img_swiper_selected" : ''}>
                    <div className="shoe_mini_box">
                        <img src={img1} alt = "350v2" className="shoe_mini_img"/>
                    </div>
                </a>
                <a href = "#img2" onClick={(e) => handleClick(e, 2)} className={selectedIndex === 2 ? "mini_img_swiper_selected" : ''}>
                    <div className="shoe_mini_box" >
                        <img src={img1} alt = "350v2" className="shoe_mini_img"/>
                    </div>
                </a>
                <a href = "#img3" onClick={(e) => handleClick(e, 3)} className={selectedIndex === 3 ? "mini_img_swiper_selected" : ''}>
                    <div className="shoe_mini_box" >
                        <img src={img1} alt = "350v2" className="shoe_mini_img"/>
                    </div>
                </a>
                <a href = "#img4" onClick={(e) => handleClick(e, 4)} className={selectedIndex === 4 ? "mini_img_swiper_selected" : ''}>
                    <div className="shoe_mini_box" >
                        <img src={img1} alt = "350v2" className="shoe_mini_img"/>
                    </div>
                </a>
            </div>
        </div>
    );
};

export default MiniImgSwiper;