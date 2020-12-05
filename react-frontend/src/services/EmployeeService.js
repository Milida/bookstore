import axios from 'axios';

const EMPLOYEE_API_BASE_URL = "http://localhost:8080/employees";

class EmployeeService {
    addEmployee(employee) {
        return axios.post(EMPLOYEE_API_BASE_URL, employee);
    }

    employeeExistsById(id) {
        return axios.get(EMPLOYEE_API_BASE_URL + '/' + id);
    }
}

export default new EmployeeService()