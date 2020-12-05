import React, { Component } from 'react';
import UserService from "../services/UserService";
import OrderService from "../services/OrderService";

class MyAccountComponent extends Component{

    constructor(props) {
        super(props)

        this.state = {
            user : {},
            orders: []
        }
    }

    componentDidMount(){
        UserService.getUserById(localStorage.getItem('userId')).then((res)  => {
            this.setState({user: res.data});
        });
        OrderService.getOrders().then((res) => {
            this.setState({ orders: res.data.filter(order => order.user.id === this.state.user.id) });
        });
    }

    editUser(){
        this.props.history.push(`/update-user/${this.state.user.id}`);
    }
    removeUser() {
        alert("Are you sure you want to delete your account?"); //TODO ?
        this.state.user.isActive = false;
        console.log('user =>' + JSON.stringify(this.state.user));
        UserService.updateUser(this.state.user, this.state.user.id).then(res =>{
            this.props.history.push(`/users/${this.state.user.id}`);
        });
        UserService.logout();
    }


    render() {
        if (this.state.user.id == null) {
            return (<div> <p>You have no access!</p></div>)
        }
        return (
            <div>
                <h2 className="text-center">Your data</h2>
                <div className="row">
                    <table className = "table table-striped table-bordered">
                        <tbody>
                            <tr>
                                <th>Firstname</th>
                                <td>{this.state.user.firstname}</td>
                            </tr>
                            <tr>
                                <th>Lastname</th>
                                <td>{this.state.user.lastname}</td>
                            </tr>
                            <tr>
                                <th>Email</th>
                                <td>{this.state.user.email}</td>
                            </tr>
                            <tr>
                                <th>Phone</th>
                                <td>{this.state.user.phone}</td>
                            </tr>
                            <tr>
                                <th>Address</th>
                                <td>{this.state.user.address}</td>
                            </tr>
                            <tr>
                                <th>Postal Code</th>
                                <td>{this.state.user.postalCode}</td>
                            </tr>
                            <tr>
                                <th>City</th>
                                <td>{this.state.user.city}</td>
                            </tr>
                        </tbody>
                    </table>
                    <div className="d-flex justify-content-between">
                        <button style={{marginRight:"10px"}} onClick ={() => this.editUser()} className="btn btn-info">Edit your data</button>
                        <button onClick={() => this.removeUser()} className="btn btn-danger">Delete your account</button>
                    </div>
                </div>
                    <h2 className="text-center">Your orders</h2>
                    <div className="row">
                        <table className="table table-striped table-bordered">
                            <thead>
                                <th>Date:</th>
                                <th>Books:</th>
                                <th>Shipment:</th>
                                <th>Payment:</th>
                                <th>Status:</th>
                                <th>Price:</th>
                            </thead>
                            <tbody>
                            {
                                this.state.orders.map(
                                    order =>
                                        <tr key={order.id}>
                                            <td>{order.date.slice(0, 10)}</td>
                                            <td><ul>{order.orderBook.map(orderbook => <li key={orderbook.book.id}>{orderbook.book.title + ", " + orderbook.quantity}</li>)} </ul></td>
                                            <td>{order.shipment.name}</td>
                                            <td>{order.payment.name}</td>
                                            <td>{order.status.name}</td>
                                            <td>{order.price}</td>
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

export default MyAccountComponent;