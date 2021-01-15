import React, { Component } from 'react';
import UserService from '../services/UserService';

class LisUsersComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            users : []
        }
    }

    componentDidMount(){
        if (localStorage.getItem('isWorker') !== "true")
            this.props.history.push("/");
        UserService.getUsers().then((res)  => {
            this.setState({users: res.data});
        });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Users List</h2>
                {/* <div className = "row">
                    <button className="btn btn-primary" onClick={this.adduser}>Add user</button>
                </div> */}
                <div className="row">
                    <table className = "table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>user's Firstname</th>
                                <th>user's Lastname</th>
                                <th>user's Email</th>
                                <th>user's Phone</th>
                                <th>user's Address</th>
                                <th>user's Postal Code</th>
                                <th>user's City</th>
                                <th>user's Role</th>
                                {/* <th>Active</th> */}

                                {/* <th>Actions</th> */}
                            </tr>
                        </thead>

                        <tbody>
                            {
                                this.state.users.map(
                                    user =>
                                    <tr key = {user.id}>
                                        <td>{user.firstname}</td>
                                        <td>{user.lastname}</td>
                                        <td>{user.email}</td>
                                        <td>{user.phone}</td>
                                        <td>{user.address}</td>
                                        <td>{user.postalCode}</td>
                                        <td>{user.city}</td>
                                        <td>{user.role.name}</td>
                                        {/* <td>{user.isActive.toString()}</td> */}
                                        {/* <td>
                                            <button onClick ={() => this.edituser(user)} className="btn btn-info">Update</button>
                                        </td> */}
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default LisUsersComponent;