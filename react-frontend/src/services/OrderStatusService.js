import axios from 'axios';

const ORDERSTATUS_API_BASE_URL = "http://localhost:8080/orderstatuses";

class OrderStatusService {
    getOrderStatuses(){
        return axios.get(ORDERSTATUS_API_BASE_URL);
    }

    getOrderStatusById(orderStatusId){
        return axios.get(ORDERSTATUS_API_BASE_URL +'/' + orderStatusId);
    }

}

export default new OrderStatusService()