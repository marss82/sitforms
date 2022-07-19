/* eslint-disable */

import React from 'react';
import {observer} from "mobx-react-lite";

const PublicFormsComponent = observer(() => {
    return (
        <div className="forms">
            <div className="wrapper">
                <h1>Public forms</h1>
                <div className="row form_search_form">
                    <div className="col-md-2">
                        <p>Form name</p>
                    </div>
                    <div className="col-md-4">
                        <input type="text"/>
                    </div>
                    <div className="col-md-2">
                        <button>Search</button>
                    </div>
                    <div className="col"></div>
                </div>
                <div className="forms-row">
                    <div className="form-item">
                        <div className="row">
                            <div className="col-md-10">
                                <p>Study questions</p>
                            </div>
                            <div className="col-md-2">
                                <a href="/form/5">Fill in a form</a>
                            </div>
                        </div>
                    </div>
                    <div className="form-item">
                        <div className="row">
                            <div className="col-md-10">
                                <p>Study questions</p>
                            </div>
                            <div className="col-md-2">
                                <a href="#">Fill in a form</a>
                            </div>
                        </div>
                    </div>
                    <div className="form-item">
                        <div className="row">
                            <div className="col-md-10">
                                <p>Study questions</p>
                            </div>
                            <div className="col-md-2">
                                <a href="#">Fill in a form</a>
                            </div>
                        </div>
                    </div>
                    <div className="form-item">
                        <div className="row">
                            <div className="col-md-10">
                                <p>Study questions</p>
                            </div>
                            <div className="col-md-2">
                                <a href="#">Fill in a form</a>
                            </div>
                        </div>
                    </div>
                    <div className="form-item">
                        <div className="row">
                            <div className="col-md-10">
                                <p>Study questions</p>
                            </div>
                            <div className="col-md-2">
                                <a href="#">Fill in a form</a>
                            </div>
                        </div>
                    </div>  
                </div>
                <div className="pagination">
                    <ul>
                        <li><a href="#" className="active">1</a></li>
                        <li><a href="#">2</a></li>
                        <li>...</li>
                        <li><a href="#">22</a></li>
                    </ul>
                </div>
            </div>
        </div>
    );
});

export default PublicFormsComponent;