import jwtDecode from 'jwt-decode';
import {$authHost, $host} from "./index";

export const registrate = async (email, username, password, name, surname) =>{
    const {data} = await $host.post('api/v1/users/registration', {email, username, password, name, surname})
    localStorage.setItem('token', data.token)
    return jwtDecode(data.token)
}

export const login = async (username, password) => {
    const {data} = await $host.post('api/v1/users/login', {username, password})
    localStorage.setItem('token', data.token)
    return jwtDecode(data.token)
}

export const findUserByEmail = async (email) => {
    const {data} = await $authHost.get('api/v1/users/' + email)
    return data
}

export const addMemberById = async (userId, groupId) => {
    const {data} = await $authHost.get('api/v1/users/' + groupId +'/add/' + userId)
    return data
}

export const fetchGroupUsers = async (groupId) => {
    const {data} = await $authHost.get('api/v1/users/' + groupId +'/users')
    return data
}

export const refresh = async () => {
    const {data} = await $authHost.get('api/v1/users/refresh')
    localStorage.setItem('token', data.token)
    return jwtDecode(data.token)
}



