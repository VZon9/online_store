import React from 'react';
import './style.css';

const Footer = () => {
    return (
        <footer className="footer">
            <div className="container">
                <div className="footer_content">
                    <div className="footer_promo">
                        <div className="footer_bubna">BUBNASHOP</div>
                        <div className="footer_desc">Complete your style with awesome sneakers from us</div>
                    </div>
                    <div className="footer_about">
                        <div className="footer_list">
                            <p>Company | Contact us | Support | Careers </p>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    );
};

export default Footer;