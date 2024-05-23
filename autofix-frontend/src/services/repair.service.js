import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/repairs/')
}

const getByCode = code => {
    return httpClient.get(`/repairs/code_${code}`)
}

const getById = id => {
    return httpClient.get(`/repairs/${id}`)
}

const create = data => {
    return httpClient.post('/repairs/', data)
}

const update = data => {
    return httpClient.put('/repairs/', data)
}

const addfinalprice = id => {
    return httpClient.put(`/repairs/calcFinalPrice/${id}`)
}

const remove = id => {
    return httpClient.delete(`/repairs/${id}`)
}

const getTotalAmount = (repairCode) => {
    return httpClient.get(`/repairs/totalAmount_${repairCode}`)
}

const getReportBodywork = () => {
    return httpClient.get('/repairs/repairTypeAmounts')
}

const getReportEngine = () => {
    return httpClient.get('/repairs/repairTypeAmountsByEngine')
}

const getReportAvgTime = () => {
    return httpClient.get('/repairs/averageRepairTimeByBrand')
}



export default { getAll, getByCode, getById, create, update, remove, addfinalprice, getTotalAmount, getReportBodywork, getReportEngine, getReportAvgTime}