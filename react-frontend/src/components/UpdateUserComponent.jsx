import React, { Component } from 'react';
import UserService from "../services/UserService";

class UpdateUserComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            password: '',
            email: '',
            firstname: '',
            lastname: '',
            phone: '',
            address: '',
            postalCode: '',
            city: '',

        }
        this.changeFirstnameHandler = this.changeFirstnameHandler.bind(this);
        this.changeLastnameHandler = this.changeLastnameHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changePhoneHandler = this.changePhoneHandler.bind(this);
        this.changeAddressHandler = this.changeAddressHandler.bind(this);
        this.changePostalCodeHandler = this.changePostalCodeHandler.bind(this);
        this.changeCityHandler = this.changeCityHandler.bind(this);
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
    }

    componentDidMount(){
        UserService.getUserById(localStorage.getItem('userId')).then((res) =>{
            let user = res.data
            this.setState({
                email: user.email,
                firstname: user.firstname,
                lastname: user.lastname,
                phone: user.phone,
                address: user.address,
                postalCode: user.postalCode,
                city: user.city
            });
        });
    }
    updateUser = (e) => {
        e.preventDefault();
        let user = {
            email: this.state.email,
            password: this.state.password,
            firstname: this.state.firstname,
            lastname: this.state.lastname,
            phone: this.state.phone,
            address: this.state.address,
            postalCode: this.state.postalCode,
            city: this.state.city
        };
        UserService.updateUser(user, this.state.id).then(res => {
            this.props.history.push(`/users/${this.state.id}`);
        }).catch(error => alert("Incorrect data!"));
    }

    changeFirstnameHandler = (event) =>{
        this.setState({firstname: event.target.value});
    }

    changeLastnameHandler = (event) =>{
        this.setState({lastname: event.target.value});
    }

    changeEmailHandler = (event) =>{
        this.setState({email: event.target.value});
    }

    changePhoneHandler = (event) =>{
        this.setState({phone: event.target.value});
    }

    changeAddressHandler = (event) =>{
        this.setState({address: event.target.value});
    }

    changeCityHandler = (event) =>{
        this.setState({city: event.target.value});
    }

    changePostalCodeHandler = (event) =>{
        this.setState({postalCode: event.target.value});
    }

    changePasswordHandler = (event) =>{
        this.setState({password: event.target.value});
    }

    cancel(){
        this.props.history.push(`/users/${this.state.user.id}`);
    }

    render() {
        return (
            <div>
                <div className="container">
                    {"\n"}
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">{"\n"}Edit your data</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Firstname:  </label>{"\n"}
                                        <input
                                            className="form-control"
                                            type="firstname"
                                            name="firstname"
                                            value={this.state.firstname}
                                            onChange={this.changeFirstnameHandler}
                                            required
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Lastname:  </label>{"\n"}
                                        <input
                                            className="form-control"
                                            type="lastname"
                                            name="lastname"
                                            value={this.state.lastname}
                                            onChange={this.changeLastnameHandler}
                                            required
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Email:  </label>{"\n"}
                                        <input
                                            typeof='email'
                                            className="form-control"
                                            type="email"
                                            name="email"
                                            value={this.state.email}
                                            onChange={this.changeEmailHandler}
                                            required
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Change password: </label>{"\n"}
                                        <input
                                            className="form-control"
                                            type="password"
                                            name="password"
                                            value={this.state.password}
                                            onChange={this.changePasswordHandler}
                                            required
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Phone:  </label>{"\n"}
                                        <input
                                            className="form-control"
                                            type="phone"
                                            name="phone"
                                            value={this.state.phone}
                                            onChange={this.changePhoneHandler}
                                            required
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Address:  </label>
                                        <input
                                            className="form-control"
                                            type="address"
                                            name="address"
                                            value={this.state.address}
                                            onChange={this.changeAddressHandler}
                                            required
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Postal Code:  </label>{"\n"}
                                        <input
                                            className="form-control"
                                            type="postalCode"
                                            name="postalCode"
                                            value={this.state.postalCode}
                                            onChange={this.changePostalCodeHandler}
                                            required
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>City:  </label>{"\n"}
                                        <input
                                            className="form-control"
                                            type="city"
                                            name="city"
                                            value={this.state.city}
                                            onChange={this.changeCityHandler}
                                            required
                                        />
                                    </div>

                                    {/* <input
            type="password"
            name="password_confirmation"
            value={this.state.password_confirmation}
            onChange={this.handleChange}
            required
          /> */}

                                    <button className="btn btn-success" onClick={this.updateUser}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{ marginLeft: "10px" }}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
export default UpdateUserComponent;