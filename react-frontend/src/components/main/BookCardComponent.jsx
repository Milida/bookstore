import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import CartService from '../../services/CartService';

class BookCardComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            book: props.book,
            userId: localStorage.getItem('userId')
        }
        this.addToCart = this.addToCart.bind(this);
    }

    addToCart() {
        CartService.addToCart(
            {
                "id": {
                    "userId": this.state.userId,
                    "bookId": this.state.book.id
                },
                "user": {
                    "id": this.state.userId
                },
                "book": {
                    "id": this.state.book.id
                },
                "quantity": 1
            }
        ).then(res => {alert("Product added to your cart")}).catch(err => alert("An error occured"))
    }

    render() {
        return (
            <div className="col-auto mb-3">
                <div className="card" style={{ width: 20 + 'rem', height: 20 + 'rem' }}>
                    <div className="card-body">
                        <h5 className="card-title"> <Link to={`books/${this.state.book.id}`}>{this.state.book.title}</Link></h5>
                        <h6 className="card-subtitle mb-2">{this.state.book.price + ' ZŁ'}</h6>
                        <p className="card-text text-justify card-text-limited"> {this.state.book.description} </p>
                    </div>
                    <div className="card-footer text-center">
                        {
                            localStorage.getItem('userId')
                            ? this.state.book.quantity < 1 
                                ? <button type="button" className="btn p-0 btn-link card-link text-muted" disabled>Product not available</button>
                                : <button type="button" className="btn p-0 btn-link card-link" onClick={this.addToCart}>Add to cart</button>
                            : <button type="button" className="btn p-0 btn-link card-link text-muted" disabled>Please login to buy this product</button>
                        }
                        
                    </div>
                </div>
            </div>
        )
    }
}

export default BookCardComponent;