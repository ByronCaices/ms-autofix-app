import httpClient from "../http-common";

const getBonusByRepairCode = (repairCode) => {
    return httpClient.get(`/repairs/orders/bonus/${repairCode}`)
}

const create = myid => {
    return httpClient.post(`/repairs/orders/${myid}`)
}

export default { getBonusByRepairCode, create }