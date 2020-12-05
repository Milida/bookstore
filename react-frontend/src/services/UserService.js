import axios from 'axios';

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

    getUserById(id) {
        return axios.get(USER_API_BASE_URL + '/' + id);
    }

    updateUser(user, userId) {
        return axios.put(USER_API_BASE_URL + '/' + userId, user);
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