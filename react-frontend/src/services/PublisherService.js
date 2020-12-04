import axios from 'axios';

const PUBLISHER_API_BASE_URL = "http://localhost:8080/publishers";

class PublisherService {

    getPublishers() {
        return axios.get(PUBLISHER_API_BASE_URL);
    }

    addPublisher(publisher) {
        return axios.post(PUBLISHER_API_BASE_URL, publisher);
    }

    getPublisherById(publisherId) {
        return axios.get(PUBLISHER_API_BASE_URL + '/' + publisherId);
    }

    updatePublisher(publisher, publisherId) {
        return axios.put(PUBLISHER_API_BASE_URL + '/' + publisherId, publisher);
    }
    removePublisher(publisher) {
        return axios.delete(PUBLISHER_API_BASE_URL + '/' + publisher.id);
    }

}

export default new PublisherService()