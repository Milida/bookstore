import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import UserService from '../services/UserService';

class HeaderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            user: false,
            isWorker: false
        }
    }

    componentDidMount() {
        this.setState({
            userId: localStorage.getItem('userId'),
            userFirstname: localStorage.getItem('userFirstname'),
            isWorker: localStorage.getItem('isWorker')
        });
    }

    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark text-white mb-4 d-flex justify-content-between">
                        <div className="d-flex">
                            <div><Link className="navbar-brand" to="/">Book Shop</Link></div>
                            {this.state.isWorker === "true" ?
                                <>
                                <div><Link className="navbar-brand" to="/books">Books</Link></div>
                                <div><Link className="navbar-brand" to="/publishers">Publishers</Link></div>
                                <div><Link className="navbar-brand" to="/authors">Authors</Link></div>
                                <div><Link className="navbar-brand" to="/categories">Categories</Link></div>
                                <div><Link className="navbar-brand" to="/users">Users</Link></div>
                                <div><Link className="navbar-brand" to = "/orders">Orders</Link></div>
                                </> : <></>
                            }
                        </div>

                        <div className="d-flex">
                            {this.state.userId ?
                                <div className="d-flex">
                                    <div><Link className="navbar-brand" to={'/users/' + this.state.userId}>{this.state.userFirstname}</Link></div>
                                    <div><Link className="navbar-brand" to="/cart">Cart</Link></div>
                                    <div><Link className="navbar-brand" to="/logout" onClick={UserService.logout}>Logout</Link></div>
                                </div> :
                                <div className="d-flex">
                                    <div><Link className="navbar-brand" to="/login">Login</Link></div>
                                    <div><Link className="navbar-brand" to="/registration">Register</Link></div>
                                </div>

                            }

                        </div>
                    </nav>
                </header>
            </div >
        );
    }
}

export default HeaderComponent;