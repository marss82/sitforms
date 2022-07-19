import {makeAutoObservable} from "mobx";

export default class UserStore{

    constructor() {
        this._isAuth = false;
        this._user = {}
        this._role = null
        this._id = null
        this._email = null
        this._subscription = null
        makeAutoObservable(this)
    }

    setId(id){
        this._id = id;
    }

    get userId(){
        return this._id;
    }

    get id(){
        return this._id;
    }

    get isAuth() {
        return this._isAuth;
    }

    get subscription() {
        return this._subscription;
    }

    get user() {
        return this._user;
    }

    get email(){
        return this._email
    }

    get role(){
        return this._role
    }

    setEmail(email){
        this._email = email
    }

    setSubscription(subscription){
        this._subscription = subscription
    }

    setRole(role){
        this._role = role
    }

    setIsAuth(bool) {
        this._isAuth = bool
    }
    setUser(user) {
        this._user = user
    }
}