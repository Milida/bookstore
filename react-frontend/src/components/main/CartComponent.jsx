import React, { Component } from 'react';
import CartService from '../../services/CartService';
import ShipmentService from '../../services/ShipmentService';
import PaymentService from '../../services/PaymentService';
import Select from 'react-select'
import OrderService from '../../services/OrderService';



class CartComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            cart: [],
            userId: localStorage.getItem('userId'),
            total: 0,
            payment: { id: 0, price: 0 },
            shipment: { id: 0, price: 0 },
            paymentList: [],
            shipmentList: []


        }
        this.removeItem = this.removeItem.bind(this);
        this.removeAll = this.removeAll.bind(this);
        this.setPayment = this.setPayment.bind(this);
        this.setShipment = this.setShipment.bind(this);
        this.makeOrder = this.makeOrder.bind(this);

    }

    componentDidMount() {
        if (!this.state.userId) {
            this.props.history.push('/');
        }
        CartService.getCart(this.state.userId).then(res => {
            this.setState({ cart: res.data });

            this.setState({ total: this.state.cart.reduce((sum, item) => sum + (item.book.price * item.quantity), 0) });
            this.setState({ total: Math.round((this.state.total * 100)) / 100 });
        });
        ShipmentService.getShipments().then(res => this.setState({
            shipmentList: res.data.map(ship => ({ value: ship, label: ship.name + ` (${ship.price} zl)`, price: ship.price }))
        }));
        PaymentService.getPayments().then(res => this.setState({
            paymentList: res.data.map(pay => ({ value: pay, label: pay.name }))
        }));
    }


    removeItem(itemId) {
        CartService.removeItem(this.state.userId, itemId).then(res => {
            //this.setState({ cart: this.state.cart.filter(item => item.book.id !== itemId) })
            this.componentDidMount();
        }).catch(err => alert('Cannot remove this item'));
    }

    removeAll(){
        CartService.removeAllUserItems(this.state.userId).then(res => {
            this.componentDidMount();
        }).catch(err => alert('Cannot remove one or more of these items'));
    }

    setPayment(e) {
        this.setState({
            payment: e.value
        });
    }

    setShipment(e) {
        this.setState({
            shipment: e.value
        });
    }

    makeOrder() {
        //to do
        //OrderService.addOrder()
    }

    render() {
        return (
            <div className="container border py-2 px-5">

                <div className="row">
                    <h2 className="text-center col-md-6 offset-md-3">Cart</h2>
                    {
                        this.state.cart !== [] &&


                        <button className="btn btn-danger ml-auto mr-1 my-1" onClick={this.removeAll}>Delete All</button>
                    }

                </div>

                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th style={{ width: 30 + '%' }}>Title</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Amount</th>
                                <th>Action</th>
                            </tr>
                        </thead>

                        <tbody>
                            {
                                this.state.cart.map(
                                    item =>
                                        <tr key={item.book.id}>
                                            <td>{item.book.title}</td>
                                            <td>{item.book.price} zł</td>
                                            <td>{item.quantity}</td>
                                            <td>{Math.round((item.quantity * item.book.price * 100)) / 100} zł</td>
                                            <td>
                                                <button onClick={() => this.removeItem(item.book.id)} className="btn btn-danger">Delete</button>
                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>

                    <div className="d-flex flex-column col-md-6 px-0">

                        <div className="d-flex input-group mb-3">
                            <div className="input-group-prepend">
                                <label style={{ minWidth: 8 + 'rem' }} className="input-group-text" htmlFor="payment">Payment</label>
                            </div>
                            <div className="flex-grow-1">
                                <Select
                                    onChange={this.setPayment}
                                    name="payment"
                                    options={this.state.paymentList}
                                    className="basic-single"
                                    classNamePrefix="select"
                                />
                            </div>
                        </div>

                        <div className="d-flex">
                            <div className="input-group-prepend">
                                <label style={{ minWidth: 8 + 'rem' }} className="input-group-text" htmlFor="shipment">Shipment</label>
                            </div>
                            <div className="flex-grow-1">
                                <Select
                                    onChange={this.setShipment}
                                    name="shipment"
                                    options={this.state.shipmentList}
                                    className="basic-single"
                                    classNamePrefix="select"
                                />
                            </div>

                        </div>


                    </div>

                    <div className="ml-auto d-flex flex-column">
                        <h4 className="float-right">Total: {Math.round((this.state.total + this.state.shipment.price) * 100) / 100} zł</h4>
                        <button onClick={this.makeOrder} className="btn-primary btn-lg">Order</button>
                    </div>



                </div>
            </div>
        )
    }
}

export default CartComponent;