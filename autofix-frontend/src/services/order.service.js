import httpClient from "../http-common";

const getBonusByRepairCode = (repairCode) => {
    return httpClient.get(`/api/v1/orders/bonus/${repairCode}`)
}

const create = myid => {
    return httpClient.post(`/api/v1/orders/${myid}`)
}

export default { getBonusByRepairCode, create }