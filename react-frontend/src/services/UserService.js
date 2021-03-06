import axios from 'axios';

const USER_API_BASE_URL = "http://localhost:8080/users";

class UserService {

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

    removeUser(userId) {
        return axios.delete(USER_API_BASE_URL + '/' + userId);
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
        localStorage.removeItem('isWorker');
        window.location.reload();
    }

}

export default new UserService()