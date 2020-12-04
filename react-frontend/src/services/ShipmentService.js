import axios from 'axios';

const SHIPMENT_API_BASE_URL = "http://localhost:8080/shipments";

class ShipmentService {
    getShipments(){
        return axios.get(SHIPMENT_API_BASE_URL);
    }

    getShipmentById(shipmentId){
        return axios.get(SHIPMENT_API_BASE_URL +'/' + shipmentId);
    }

}

export default new ShipmentService()