import React, {useEffect, useState, Children, cloneElement} from 'react';
import './style.css'
import KeyboardArrowRightIcon from '@mui/icons-material/KeyboardArrowRight';
import KeyboardArrowLeftIcon from '@mui/icons-material/KeyboardArrowLeft';

const ITEM_WIDTH = 680
const Carousel = ( {children} ) => {

    const [items, setItems] = useState([])
    const [offset, setOffset]  = useState(0)
    const handleLeftArrowClick = () => {
        console.log("left arrow click")

        setOffset((currentOffset) => {
            const newOffset = currentOffset + ITEM_WIDTH
            console.log(newOffset)
            return Math.min(newOffset, 0)
        })
    }

    const handleRightArrowClick = () => {
        console.log("right arrow click")

        setOffset((currentOffset) => {
            const newOffset = currentOffset - ITEM_WIDTH

            const maxOffset = -(ITEM_WIDTH * (items.length - 1))

            console.log(newOffset)
            return Math.max(newOffset, maxOffset)
        })
    }

    useEffect(() => {
        setItems(
            Children.map(children, (child) => {
                return cloneElement(child, {
                    style: {
                        height: '100%',
                        minWidth: `${ITEM_WIDTH}px`,
                        maxWidth: `${ITEM_WIDTH}px`
                    },
                })
            })
        )
    }, [children]);

    return (
        <div className="main-container">
            <div className="arrow_box">
                <KeyboardArrowLeftIcon className="arrow" onClick={handleLeftArrowClick}/>
            </div>
            <div className="window">
                <div className="all-items-container"
                style={{
                    transform: `translateX(${offset}px)`
                }}
                >{items}
                </div>
            </div>
            <div>
                <KeyboardArrowRightIcon className="arrow" onClick={handleRightArrowClick} />
            </div>
        </div>
    );
};

export default Carousel;