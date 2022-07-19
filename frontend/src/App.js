import React, {useContext, useEffect, useState} from 'react';
import {BrowserRouter} from "react-router-dom";
import AppRouter from "./component/AppRouter";
import NavBar from "./component/NavBar";
import Footer from "./component/Footer";
import {Context} from "./index";
import {refresh} from "./http/userAPI";
import {Spinner} from "react-bootstrap";
import {observer} from "mobx-react-lite";


const App = observer(() => {
    const {user} = useContext(Context)
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        refresh().then(data => {
            user.setUser(data)
            user.setRole(data.role)
            user.setId(data.id)
            user.setIsAuth(true)
            user.setEmail(data.sub)
        }).finally(() => {
            setLoading(false)
        })
    }, [])
    if (loading) {
        return <Spinner animation={"grow"}/>
    }
    return (
        <BrowserRouter>
            <NavBar />
            <AppRouter />
            <Footer />
        </BrowserRouter>
    );
});

export default App;