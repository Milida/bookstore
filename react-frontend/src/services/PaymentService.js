import axios from 'axios';

const PAYMENT_API_BASE_URL = "http://localhost:8080/payments";

class PaymentService {
    getPayments(){
        return axios.get(PAYMENT_API_BASE_URL);
    }

    getPaymentById(paymentId){
        return axios.get(PAYMENT_API_BASE_URL +'/' + paymentId);
    }

}

export default new PaymentService()