/* eslint-disable */
import React from 'react';
import {observer} from "mobx-react-lite";

const NotificationsPage = observer(() => {

    return (
        <div className="page">
            <div className="wrapper">
                <h1>Notifications</h1>
                <div className="notifications">
                    <div className="notification-item">
                        <p>Notification message</p>
                    </div>

                    <div className="notification-item">
                        <p>Notification message</p>
                    </div>

                    <div className="notification-item">
                        <p>Notification message</p>
                    </div>

                    <div className="notification-item">
                        <p>Notification message</p>
                    </div>

                    <div className="notification-item">
                        <p>Notification message</p>
                    </div>

                    <div className="notification-item">
                        <p>Notification message</p>
                    </div>

                    <div className="notification-item">
                        <p>Notification message</p>
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

export default NotificationsPage;