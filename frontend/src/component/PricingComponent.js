/* eslint-disable */

import React from 'react';
import {observer} from "mobx-react-lite";

const NavBar = observer(() => {
    return (
        <div className="row">
            <div className="col"></div>
            <div className="col-md-6">
                <h1 className="pricing-title">Free vs Premium subscriptions</h1>

                <table className="table table-bordered border-dark">
                    <thead>
                    <tr>
                        <th>Funkce</th>
                        <th>Free</th>
                        <th>Premium</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Form creation</td>
                        <td>5x in month</td>
                        <td><i className="fa-solid fa-check"></i></td>
                    </tr>
                    <tr>
                        <td>Group creation</td>
                        <td>5x for user</td>
                        <td><i className="fa-solid fa-check"></i></td>
                    </tr>
                    <tr>
                        <td>Assigning users to group</td>
                        <td>Max 10 people</td>
                        <td><i className="fa-solid fa-check"></i></td>
                    </tr>
                    <tr>
                        <td>Import statistics from form</td>
                        <td>2x in month</td>
                        <td><i className="fa-solid fa-check"></i></td>
                    </tr>
                    <tr>
                        <td>Export statistics in pdf</td>
                        <td>5x in month</td>
                        <td><i className="fa-solid fa-check"></i></td>
                    </tr>
                    <tr>
                        <td>Form answers editing</td>
                        <td><i className="fa-solid fa-check"></i></td>
                        <td><i className="fa-solid fa-check"></i></td>
                    </tr>
                    <tr>
                        <td>Sharing form with link</td>
                        <td><i className="fa-solid fa-check"></i></td>
                        <td><i className="fa-solid fa-check"></i></td>
                    </tr>
                    <tr>
                        <td>Sharing form with email</td>
                        <td><i className="fa-solid fa-check"></i></td>
                        <td><i className="fa-solid fa-check"></i></td>
                    </tr>
                    <tr>
                        <td>Sharing form with QR code</td>
                        <td><i className="fa-solid fa-check"></i></td>
                        <td><i className="fa-solid fa-check"></i></td>
                    </tr>
                    <tr>
                        <td>Application notifications</td>
                        <td><i className="fa-solid fa-check"></i></td>
                        <td><i className="fa-solid fa-check"></i></td>
                    </tr>
                    <tr>
                        <td>Email notifications</td>
                        <td><i className="fa-solid fa-check"></i></td>
                        <td><i className="fa-solid fa-check"></i></td>
                    </tr>
                    <tr>
                        <td>Form answers statistics generating</td>
                        <td><i className="fa-solid fa-check"></i></td>
                        <td><i className="fa-solid fa-check"></i></td>
                    </tr>
                    <tr className="buy-subscription">
                        <td>Buy subscription</td>
                        <td>
                            <p>0$/month</p>
                            <button>Choose</button>
                        </td>
                        <td>
                            <p>10$/month</p>
                            <button>Choose</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div className="col"></div>
        </div>
    );
});

export default NavBar;