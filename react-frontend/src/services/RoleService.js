import axios from 'axios';

const ROLE_API_BASE_URL = "http://localhost:8080/roles";

class RoleService {
    getRoleById(roleId){
        return axios.get(ROLE_API_BASE_URL +'/' + roleId);
    }
}

export default new RoleService()