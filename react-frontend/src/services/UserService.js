import axios from 'axios';
import HeaderComponent from '../components/HeaderComponent';

const USER_API_BASE_URL = "http://localhost:8080/users";

class UserService {

    static user = '';
    static isWorker = false;

    getUsers() {
        return axios.get(USER_API_BASE_URL);
    }
    addUser(user) {
        return axios.post(USER_API_BASE_URL, user);
    }

    getLoggedUser(){
        return this.user;
    }

    login(credentials) {
        return axios.post(USER_API_BASE_URL + '/login', credentials)
    }

    logout() {
        localStorage.removeItem('userId');
        localStorage.removeItem('userFirstname');
        localStorage.removeItem('userLastname');
        window.location.reload();
    }

}

export default new UserService()