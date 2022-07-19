/* eslint-disable */

import React, {useContext} from 'react';
import {observer} from "mobx-react-lite";
import {Context} from "../index";
import Sidebar from "./Sidebar";

const NavBar = observer(() => {

    const {user} = useContext(Context)
    console.log(user)
    return (
        <header>
            {user.isAuth ?
                <div>
                    <Sidebar pageWrapId={'page-wrap'} outerContainerId={'outer-container'} />
                </div>
                :""
            }
            <div className="wrapper">
                <div className="row">
                    <div className="col-md-3">
                        <a href="/" className="logo">SIT FORMS</a>
                    </div>
                    <div className="col-md-6"></div>
                    <div className="col-md-3">
                        <nav className="header_nav">
                            <ul>
                                <li><a href="/pricing">Pricing</a></li>
                                {user.isAuth ?
                                    <li><a href="/profile"><i className="fa-solid fa-circle-user"></i></a></li>
                                    :
                                    <li><a href="/login">Login</a></li>
                                }
                                {user.isAuth ?
                                    <li><a href="/notifications"><i className="fa-solid fa-bell"></i></a></li>
                                    :
                                    ''
                                }

                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </header>
    );
});

export default NavBar;