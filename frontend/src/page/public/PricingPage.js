/* eslint-disable */
import React from 'react';
import {observer} from "mobx-react-lite";
import PricingComponent from "../../component/PricingComponent";

const PricingPage = observer(() => {

    return (
        <div className="wrapper">
            <PricingComponent />

        </div>
    );
});

export default PricingPage;