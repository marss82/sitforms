/* eslint-disable */

import React from 'react';
import {Navbar} from "react-bootstrap";
import {observer} from "mobx-react-lite";

const Footer = observer(() => {
    return (
        <footer>
            <div className="wrapper">
                <div className="row">
                    <div className="col"></div>
                    <div className="col-md-4">
                        <div className="logo">
                            <a href="#" className="logo">SIT FORMS</a>
                        </div>
                    </div>
                    <div className="col"></div>
                </div>
            </div>
        </footer>
    );
});

export default Footer;