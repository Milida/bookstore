import React, {Component} from 'react';
import OrderService from '../services/OrderService';

class ListOrdersComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            orders: []
        }
        this.removeOrder = this.removeOrder.bind(this);
        this.editOrder = this.editOrder.bind(this);

    }

    componentDidMount() {
        OrderService.getOrders().then((res) => {
            this.setState({ orders: res.data });
        });
    }
    editOrder(order){
        this.props.history.push(`/update-order/${order.id}`);
    }

    removeOrder(orderToRemove) {
        OrderService.removeOrder(orderToRemove).then(res =>
            this.setState({ orders: this.state.orders.filter(order => order.id !== orderToRemove.id) }));
    }

    render() {
        console.log(this.state.orders);
        return (
            <div>
                <h2 className="text-center">Order List</h2>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th>Firstname</th>
                            <th>Lastname</th>
                            <th>Books</th>
                            <th>Price</th>
                            <th>Shipment</th>
                            <th>Status</th>
                            <th>Payment</th>
                            <th>Date</th>
                            <th>Actions</th>
                        </tr>
                        </thead>

                        <tbody>
                        {
                            this.state.orders.map(
                                order =>
                                    <tr key={order.id}>
                                        <td>{order.user.firstname}</td>
                                        <td>{order.user.lastname}</td>
                                        <td><ul>{order.orderBook.map(orderbook => <li key={orderbook.book.id}>{orderbook.book.title + ", " + orderbook.quantity}</li>)} </ul></td>
                                        <td>{order.price}</td>
                                        <td>{order.shipment.name}</td>
                                        <td>{order.status.name}</td>
                                        <td>{order.payment.name}</td>
                                        <td>{order.date.slice(0, 10)}</td>
                                        <td>
                                            <button onClick ={() => this.editOrder(order)} className="btn btn-info">Update</button>
                                        </td>
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

export default ListOrdersComponent;