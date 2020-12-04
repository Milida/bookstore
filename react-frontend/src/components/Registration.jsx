import React, { Component } from "react";
import UserService from "../services/UserService";

class Registration extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: '',
      password: '',
      firstname: '',
      lastname: '',
      phone: '',
      address: '',
      postalCode: '',
      city: '',
    };

    this.handleChange = this.handleChange.bind(this);
    this.saveUser = this.saveUser.bind(this);
  }

  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  saveUser = (e) => {
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
    UserService.addUserr(user).then(res => {
      this.props.history.push('/');
    }).catch(error => alert("This user already exists!"));
  }

  render() {
    return (
      <div>
        <div className="container">
          {"\n"}
          <div className="row">
            <div className="card col-md-6 offset-md-3 offset-md-3">
              <h3 className="text-center">{"\n"}Register to our BookStore</h3>
              <div className="card-body">
                <form onSubmit={this.saveUser}>
                  <div className="form-group">
                    <label>Firstname:  </label>{"\n"}
                    <input
                      type="firstname"
                      name="firstname"
                      placeholder="Firstname"
                      value={this.state.firstname}
                      onChange={this.handleChange}
                      required
                    />
                  </div>
                  <div className="form-group">
                    <label>Lastname:  </label>{"\n"}
                    <input
                      type="lastname"
                      name="lastname"
                      placeholder="Lastname"
                      value={this.state.lastname}
                      onChange={this.handleChange}
                      required
                    />
                  </div>
                  <div className="form-group">
                    <label>Email:  </label>{"\n"}
                    <input
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
                      type="password"
                      name="password"
                      placeholder="Password"
                      value={this.state.password}
                      onChange={this.handleChange}
                      required
                    />
                  </div>
                  <div className="form-group">
                    <label>Phone:  </label>{"\n"}
                    <input
                      type="phone"
                      name="phone"
                      placeholder="Phone"
                      value={this.state.phone}
                      onChange={this.handleChange}
                      required
                    />                  
                    </div>
                  <div className="form-group">
                    <label>Address:  </label>{"\n"}
                    <input
                      type="address"
                      name="address"
                      placeholder="Address"
                      value={this.state.address}
                      onChange={this.handleChange}
                      required
                    />
                  </div>
                  <div className="form-group">
                    <label>Postal Code:  </label>{"\n"}
                    <input
                      type="postalCode"
                      name="postalCode"
                      placeholder="PostalCode"
                      value={this.state.postalCode}
                      onChange={this.handleChange}
                      required
                    />
                  </div>
                  <div className="form-group">
                    <label>City:  </label>{"\n"}
                    <input
                      type="city"
                      name="city"
                      placeholder="City"
                      value={this.state.city}
                      onChange={this.handleChange}
                      required
                    />
                  </div>

                  {/* <input
            type="password"
            name="password_confirmation"
            placeholder="Password confirmation"
            value={this.state.password_confirmation}
            onChange={this.handleChange}
            required
          /> */}

                  <button type="submit">Register</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default Registration;