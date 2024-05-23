import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/v1/discbonus/')
}

const getById = id => {
    return httpClient.get(`/api/v1/discbonus/${id}`)
}

const remove = id => {
    return httpClient.deleted(`/api/v1/discbonus/${id}`)
}

const update = data => {
    return httpClient.put('/api/v1/discbonus/', data)
}

const create = data => {
    return httpClient.post('/api/v1/discbonus/', data)
}

export default { getAll, remove, update, create, getById }