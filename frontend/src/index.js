import React, {createContext} from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import UserStore from "./store/UserStore";
import FormStore from "./store/FormStore";

export const Context = createContext(null)
ReactDOM.render(
    <Context.Provider value={{
        user: new UserStore(),
        form: new FormStore()
    }}>
        <App />
    </Context.Provider>,
    document.getElementById('root')
);