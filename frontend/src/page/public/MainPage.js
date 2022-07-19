/* eslint-disable */
import React from 'react';
import {observer} from "mobx-react-lite";
import PricingComponent from "../../component/PricingComponent";

const MainPage = observer(() => {

    return (
        <div className="main_page">
            <div className="wrapper">
                <div className="main_public_forms">
                    <a href="public-forms">Public forms</a>
                </div>
                <PricingComponent />
            </div>
        </div>
    );
});

export default MainPage;