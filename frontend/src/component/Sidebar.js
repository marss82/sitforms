import React from 'react';
import { slide as Menu } from 'react-burger-menu';

export default props => {

    const logout = async (e) => {
        e.preventDefault();
        try {
            localStorage.removeItem("token");
            window.location.href = "/"
        } catch (e) {
            alert(e.response.data.message)
        }
    }

    return (
        <Menu>
            <a className="menu-item" href="/form-creation">
                Create new form
            </a>
            <a className="menu-item" href="/group-creation">
                Create new group
            </a>
            <h5 className="menu_title">My groups</h5>
            <a className="menu-item" href="/assigned/groups">
                My groups
            </a>
            <a className="menu-item" href="/managed/groups">
                Managed groups
            </a>
            <h5 className="menu_title">My forms</h5>
            <a className="menu-item" href="/private/forms">
                Private forms
            </a>
            <a className="menu-item" href="/public/forms">
                Public forms
            </a>
            <a className="menu-item" href="/managed/forms">
                Managed forms
            </a>

            <br/><br/><br/>
            <button onClick={logout}>Logout</button>
        </Menu>
    );
};