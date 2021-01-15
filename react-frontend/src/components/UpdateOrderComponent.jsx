import React, { Component } from 'react';
import OrderService from '../services/OrderService';
import OrderStatusService from "../services/OrderStatusService";
import ShipmentService from "../services/ShipmentService";
import PaymentService from "../services/PaymentService";
import Select from "react-select";

class UpdateOrderComponent extends Component{
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            user: {},
            date: '',
            orderBook: [],
            price: '',
            status: {},
            shipment: {},
            payment: {},
            statusesList: [],
            shipmentsList: [],
            paymentsList: [],
            packing: {}
        }
        this.changeStatusHandler = this.changeStatusHandler.bind(this);
        this.changeShipmentHandler = this.changeShipmentHandler.bind(this);
        this.changePaymentHandler = this.changePaymentHandler.bind(this);
        this.updateOrder = this.updateOrder.bind(this);
    }
    componentDidMount() {
        if (localStorage.getItem('isWorker') !== "true")
            this.props.history.push("/");
        OrderService.getOrderById(this.state.id).then((res) => {
            let order = res.data;
            this.setState({
                user: order.user,
                date: order.date,
                orderBook: order.orderBook,
                status: order.status,
                price: order.price,
                shipment: order.shipment,
                payment: order.payment,
                dedicatedPrice: order.dedicatedPrice,
                packing: order.packing
            });
        });
        OrderStatusService.getOrderStatuses().then(res => {
            let options = res.data.map(status => {
                return {value: {id: status.id, name: status.name}, label: status.name}
            })
            this.setState({statusesList: options});
        })
        ShipmentService.getShipments().then(res => {
            let options = res.data.map(shipment => {
                return {value: {id: shipment.id, name: shipment.name}, label: shipment.name}
            })
            this.setState({shipmentsList: options});
        })
        PaymentService.getPayments().then(res => {
            let options = res.data.map(payment => {
                return {value: {id: payment.id, name: payment.name}, label: payment.name}
            })
            this.setState({paymentsList: options});
        })
    }

    updateOrder = (e) => {
        e.preventDefault();

        let order = {
            user: this.state.user, date: this.state.date, orderBook: this.state.orderBook, status: this.state.status,
            price: this.state.price, shipment: this.state.shipment, payment: this.state.payment, dedicatedPrice: this.dedicatedPrice,
            packing: this.state.packing
        };
        console.log('order =>' + JSON.stringify(order));
        console.log('price =>' + this.state.user.id);

        OrderService.updateOrder(order, this.state.id).then(() => {
            this.props.history.push('/orders');
        }).catch(error => {
            alert("Invalid field values");
        });
    }

    changeStatusHandler = (event) => {
        if (event) {
            this.setState({ status: event.value });
        }
    }

    changePaymentHandler = (event) => {
        if (event) {
            this.setState({ payment: event.value });
        }
    }

    changeShipmentHandler = (event) => {
        if (event) {
            this.setState({ shipment: event.value });
        }
    }

    cancel() {
        this.props.history.push('/orders');
    }

    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Update Order</h3>
                            <div className="card-body">
                                <p>User: {this.state.user.firstname + " " + this.state.user.lastname}</p>
                                <p>{this.state.orderBook.map(orderbook => <li key={orderbook.book.id}>{orderbook.book.title + ", " + orderbook.quantity}</li>)}</p>
                                <p>Price: {this.state.dedicatedPrice}</p>
                                <p>Date: {this.state.date.slice(0, 10)}</p>
                                    <form>
                                        <div className="form-group">
                                            <label>Status:</label>
                                            <Select onChange={this.changeStatusHandler}  options={this.state.statusesList} />
                                        </div>
                                        <div className="form-group">
                                            <label>Shipment:</label>
                                            <Select onChange={this.changeShipmentHandler} options={this.state.shipmentsList}/>
                                        </div>
                                        <div className="form-group">
                                            <label>Payment:</label>
                                            <Select onChange={this.changePaymentHandler} options={this.state.paymentsList}/>
                                        </div>
                                    <button className="btn btn-success" onClick={this.updateOrder}>Save</button>
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

export default UpdateOrderComponent;