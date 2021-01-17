import axios from 'axios';

const RATES_API_BASE_URL = "http://localhost:8080/rates";

class RateService {
    getRates() {
        return axios.get(RATES_API_BASE_URL);
    }

    setRates(rates) {
        return axios.post(RATES_API_BASE_URL, rates);
    }
}

export default new RateService()