/* eslint-disable */
import React, {useContext, useState} from 'react';
import {observer} from "mobx-react-lite";
import {Context} from "../../index";
import {Button} from "react-bootstrap";
import {login} from "../../http/userAPI";


const LoginPage = observer(() => {

    const {user} = useContext(Context)
    const [username,setUsername] = useState('')
    const [password,setPassword] = useState('')

    const click = async (e) => {
        e.preventDefault();
        try {
            console.log(username, password)
            let data;
            data = await login(username, password);
            user.setUser(data)
            user.setRole(data.role)
            user.setIsAuth(true)
            user.setEmail(data.sub)
            window.location.href = '/'
        } catch (e) {
            alert(e.response.data.message)
        }
    }

    return (
        <div className="registration_page">
            <div className="wrapper">
                <h1>Login Page</h1>
                <div className="registration_form">
                    <div className="sign-in">
                        <h4>Sign in</h4>
                        <input type="text" value={username} onChange={e => setUsername(e.target.value)} placeholder="Email"/><br/>
                        <input type="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="Password"/>
                        <h5>Forgot your password?</h5>
                        <Button onClick={click} type="submit">Sign in</Button>
                    </div>
                    <div className="sign-up">
                        <div className="sign-up-form">
                            <h3>Hello, Friend!</h3>
                            <p>Enter your personal details and start journey with us</p>
                            <a href="/registration">Sign up</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
});

export default LoginPage;