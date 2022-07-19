import {$authHost, $host} from "./index";


export const createForm = async (formName, description, authorId, formType, questionEntities) => {
    const {data} = await $authHost.post('api/v1/forms', {formName, description, authorId, formType, questionEntities})
    return data;
}

export const sendAnswers = async (formId, answers) => {
    const {data} = await $authHost.post('api/v1/forms/fill/public', {formId, answers})
    return data;
}


export const fetchManagedForms = async (authorId) => {
    const {data} = await $authHost.get('api/v1/forms/managed/' + authorId)
    return data;
}

export const fetchPublicForms = async () => {
    const {data} = await $authHost.get('api/v1/forms/public/' )
    return data;
}

export const fetchPrivateForms = async (userId) => {
    const {data} = await $authHost.get('api/v1/forms/private/' + userId)
    return data;
}



export const fetchFormQuestions = async (id) => {
    const {data} = await $authHost.get('api/v1/forms/'+id +'/questions')
    return data;
}

export const increaseViewsCount = async (id) => {
    const {data} = await $authHost.get('api/v1/forms/'+id+'/statistics/views')
    return data;
}

export const fetchFormStatistics = async (formId, userId) => {
    const {data} = await $authHost.get('api/v1/forms/'+formId +'/statistics/'+userId)
    return data;
}


