/* eslint-disable */
import React from 'react';
import {observer} from "mobx-react-lite";

const ProfilePage = observer(() => {

    return (
        <div className="page">
            <div className="wrapper">
                <h1>Profile page</h1>
                <div className="profile row">
                    <div className="col"></div>
                    <div className="col-md-5 profile-left">
                        <div className="profile-image">
                            <i className="fa-solid fa-circle-user"></i>
                        </div>
                        <h2>Sam smith</h2>
                        <button>Log out</button>
                    </div>
                    <div className="col-md-5 profile-right">
                        <ul>
                            <li><a href="#">Change password</a></li>
                            <li><a href="#">Choose subscription</a></li>
                            <li><a href="#">Delete profile</a></li>
                        </ul>
                    </div>
                    <div className="col"></div>
                </div>
            </div>
        </div>
    );
});

export default ProfilePage;