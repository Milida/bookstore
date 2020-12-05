import React, { Component } from "react";
import UserService from "../services/UserService";
import EmployeeService from "../services/EmployeeService";

class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            email: '',
            password: ''
        }


        this.handleChange = this.handleChange.bind(this);
        this.login = this.login.bind(this);
    }

    componentDidMount() {
        if (localStorage.getItem('userId'))
            this.props.history.push("/");
    }


    login(e) {
        e.preventDefault();
        let credentials = {
            email: this.state.email,
            password: this.state.password
        }
        UserService.login(credentials).then(res => {
            localStorage.setItem('userId', res.data.id);
            localStorage.setItem('userFirstname', res.data.firstname);
            EmployeeService.employeeExistsById(localStorage.getItem('userId')).then(res => {
                localStorage.setItem('isWorker', res.data);
            });
            this.props.history.push('/');
            window.location.reload();
        }).catch(error => alert("Invalid login or password!"));
    }

    handleChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    render() {
        return (
            <div className="container card col-md-6">
                <h3 className="text-center">{"\n"}Login</h3>
                <div className="card-body">
                    <form onSubmit={this.login}>
                        <div className="form-group">
                            <label>Email:  </label>
                            <input
                                className="form-control"
                                type="email"
                                name="email"
                                placeholder="Email"
                                value={this.state.email}
                                onChange={this.handleChange}
                                required
                            />
                        </div>

                        <div className="form-group">
                            <label>Password: </label>{"\n"}
                            <input
                                className="form-control"
                                type="password"
                                name="password"
                                placeholder="Password"
                                value={this.state.password}
                                onChange={this.handleChange}
                                required
                            />
                        </div>
                        <button className="btn-primary btn-lg mt-5 btn-block" type="submit">Login</button>
                    </form>
                </div>
            </div>
        );
    }
}

export default Login