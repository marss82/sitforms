import {$authHost} from "./index";

export const fetchAssignedGroups = async (userId) => {
    const {data} = await $authHost.get('api/v1/groups/assigned/' + userId)
    return data;
}


export const fetchManagedGroups = async (userId) => {
    const {data} = await $authHost.get('api/v1/groups/managed/' + userId)
    return data;
}

export const createGroup = async (name, authorId, users) => {
    const {data} = await $authHost.post('api/v1/groups', {name, authorId, users})
    return data;
}


