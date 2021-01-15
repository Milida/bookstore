import React, { Component } from 'react';
import UserService from "../services/UserService";
import OrderService from "../services/OrderService";
import DeleteAccountAlertComponent from "./DeleteAccountAlertComponent";
import RoleService from "../services/RoleService";

class MyAccountComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            user: {},
            orders: [],
            role: {}
        }

    }

    componentDidMount() {
        if (!localStorage.getItem('userId'))
            this.props.history.push("/");
        UserService.getUserById(localStorage.getItem('userId')).then((res) => {
            this.setState({ user: res.data});
            console.log(res.data);
            RoleService.getRoleById(res.data.role.id).then(res => {
                console.log(res.data)
                this.setState({role: res.data});
            })
        });
        OrderService.getOrders().then((res) => {
            this.setState({ orders: res.data.filter(order => order.user.id === this.state.user.id) });
        });
    }

    editUser() {
        this.props.history.push(`/update-user/${this.state.user.id}`);
    }


    render() {
        return (
            <div>
                <h2 className="text-center">Your data</h2>
                <div className="row">
                    <table className="table table-striped table-bordered">
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
                            <tr>
                                <th>Role</th>
                                <td>{this.state.role.name}</td>
                            </tr>
                        </tbody>
                    </table>
                    {localStorage.getItem('isWorker') === "false" &&
                    <div className="d-flex justify-content-between">
                        <button style={{marginRight: "847px"}} onClick={() => this.editUser()}
                                className="btn btn-info">Edit your data
                        </button>
                        <DeleteAccountAlertComponent/>
                    </div>
                    }
                </div>
                <div>
                    <h2 className="text-center">Your orders</h2>
                    <div className="row">
                        <table className="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th>Date:</th>
                                <th>Books:</th>
                                <th>Shipment:</th>
                                <th>Payment:</th>
                                <th>Status:</th>
                                <th>Price:</th>
                                <th>Packing:</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.orders.map(
                                    order =>
                                        <tr key={order.id}>
                                            <td>{order.date.slice(0, 10)}</td>
                                            <td>
                                                <ul>{order.orderBook.map(orderbook => <li
                                                    key={orderbook.book.id}>{orderbook.book.title + ", " + orderbook.quantity}</li>)} </ul>
                                            </td>
                                            <td>{order.shipment.name}</td>
                                            <td>{order.payment.name}</td>
                                            <td>{order.status.name}</td>
                                            <td>{order.dedicatedPrice}</td>
                                            {order.packing ?
                                                <td>{order.packing.dedication}</td>
                                                : <td/>
                                            }
                                        </tr>
                                )
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        );
    }
}

export default MyAccountComponent;