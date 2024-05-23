import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/repairs/discbonus/')
}

const getById = id => {
    return httpClient.get(`/repairs/discbonus/${id}`)
}

const remove = id => {
    return httpClient.deleted(`/repairs/discbonus/${id}`)
}

const update = data => {
    return httpClient.put('/repairs/discbonus/', data)
}

const create = data => {
    return httpClient.post('/repairs/discbonus/', data)
}

export default { getAll, remove, update, create, getById }