import React, { Component } from 'react';


class CartComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            cart: []
        }
    }

    componentDidMount() {

    }

    render() {
        return (
            <div className="container border py-2 px-5">

                <div className="row">
                        <h2 className="text-center col-md-6 offset-md-3">Cart</h2>
                        <button className="btn btn-danger ml-auto mr-1 my-1">Delete All</button>
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
                            <tr>
                                <td>Book1</td>
                                <td>8.99 zł</td>
                                <td>2</td>
                                <td>{2 * 8.99} zł</td>
                                <td>
                                    <button className="btn btn-danger">Delete</button>
                                </td>
                            </tr>

                            <tr>
                                <td>Book2</td>
                                <td>12.99 zł</td>
                                <td>1</td>
                                <td>12.99 zł</td>
                                <td>
                                    <button className="btn btn-danger">Delete</button>
                                </td>
                            </tr>

                            <tr>
                                <td>Book3</td>
                                <td>15.99 zł</td>
                                <td>3</td>
                                <td>{3 * 15.99} zł</td>
                                <td>
                                    <button className="btn btn-danger">Delete</button>
                                </td>
                            </tr>



                        </tbody>
                    </table>

                    <div className="d-flex flex-column col-md-4 px-0">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label style={{ minWidth: 8 + 'rem' }} class="input-group-text" for="payment">Payment</label>
                            </div>
                            <select class="custom-select" id="payment">
                                <option selected value="1">PayU (0.00 zł)</option>
                                <option value="2">Two</option>
                                <option value="3">Three</option>
                            </select>
                        </div>
                        <div className="input-group">
                            <div class="input-group-prepend">
                                <label style={{ minWidth: 8 + 'rem' }} class="input-group-text" for="inputGroupSelect01">Shipment</label>
                            </div>
                            <select class="custom-select" id="inputGroupSelect01">
                                <option selected value="1">Inpost (8.99 zł)</option>
                                <option value="2">Two</option>
                                <option value="3">Three</option>
                            </select>
                        </div>


                    </div>

                    <div className="ml-auto d-flex flex-column">
                        <h4 className="float-right">Total: {8.99 + 47.97 + 12.99 + 17.98} zł</h4>
                        <button className="btn-primary btn-lg">Order</button>
                    </div>



                </div>
            </div>
        )
    }
}

export default CartComponent;