/* eslint-disable */
import React, {useContext, useState} from 'react';
import {observer} from "mobx-react-lite";
import {Button} from "react-bootstrap";
import {Context} from "../../index";
import {registrate} from "../../http/userAPI";

const RegistrationPage = observer(() => {
    const {user} = useContext(Context)

    const [email,setEmail] = useState('')
    const [name,setName] = useState('')
    const [username,setUsername] = useState('')
    const [surname,setSurname] = useState('')
    const [password,setPassword] = useState('')

    const sendForm = async (e) => {
        e.preventDefault();
        try {
            console.log(email, username, password, name, surname);
            let data;
            data = await registrate(email, username, password, name, surname);
            window.location.href = '/login'
            alert("Success registration!")
        } catch (e) {
            alert(e.response.data.message)
        }
    }

    return (
        <div className="registration_page">
            <div className="wrapper">
                <h1>Registration Page</h1>
                <div className="registration">
                    <form>
                        <div className="row">
                            <div className="col-md-6 mb-5">
                                <label htmlFor="name" className="form-label">Name</label>
                                <input type="text" value={name} onChange={e => setName(e.target.value)} className="form-control" id="name" required/>
                            </div>
                            <div className="col-md-6 mb-5">
                                <label htmlFor="surname" className="form-label">Surname</label>
                                <input type="text" value={surname} onChange={e => setSurname(e.target.value)} className="form-control" id="surname" required/>
                            </div>
                            <div className="col-md-6 mb-5">
                                <label htmlFor="surname" className="form-label">Username</label>
                                <input type="text" value={username} onChange={e => setUsername(e.target.value)} className="form-control" id="surname" required/>
                            </div>
                            <div className="col-md-6 mb-5">
                                <label htmlFor="email" className="form-label">Email</label>
                                <input type="email" value={email} onChange={e => setEmail(e.target.value)} className="form-control" id="email" required/>
                            </div>
                            <div className="col-md-6 mb-5">
                                <label htmlFor="password" className="form-label">Password</label>
                                <input type="password" value={password} onChange={e => setPassword(e.target.value)} className="form-control" id="password" required/>
                            </div>
                            <div className="col-md-3">
                                <button type="submit" className="btn btn-primary" onClick={sendForm}>Send form</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
});

export default RegistrationPage;