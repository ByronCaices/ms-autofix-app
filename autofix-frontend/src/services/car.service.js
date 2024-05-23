import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/v1/cars/')
}

const getByPlate = plate => {
    return httpClient.get(`/api/v1/cars/${plate}`)
}

const create = data => {
    return httpClient.post('/api/v1/cars/', data)
}

const update = data => {
    return httpClient.put('/api/v1/cars/updt', data)
}

const remove = plate => {
    return httpClient.delete(`/api/v1/cars/${plate}`)
}

export default { getAll, getByPlate, create, remove, update}