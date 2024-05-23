import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/cars/')
}

const getByPlate = plate => {
    return httpClient.get(`/cars/${plate}`)
}

const create = data => {
    return httpClient.post('/cars/', data)
}

const update = data => {
    return httpClient.put('/cars/updt', data)
}

const remove = plate => {
    return httpClient.delete(`/cars/${plate}`)
}

export default { getAll, getByPlate, create, remove, update}