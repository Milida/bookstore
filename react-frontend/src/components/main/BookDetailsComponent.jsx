import React, { Component } from 'react';
import BookService from '../../services/BookService';
import CartService from "../../services/CartService";


class BookDetailsComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            title: '',
            authors: [],
            publisher: null,
            price: '',
            quantity: '',
            categories: [],
            description: '',
        }
        this.addToCart = this.addToCart.bind(this);
    }

    componentDidMount() {
        BookService.getBookById(this.state.id).then((res) => {
            let book = res.data;
            this.setState({
                title: book.title,
                authors: book.authors,
                publisher: book.publisher.name,
                categories: book.categories,
                description: book.description,
                price: book.price,
                quantity: book.quantity,
                cartQuantity: 1
            });
        });
        this.changeQuantityHandler = this.changeQuantityHandler.bind(this);
    }

    addToCart() {
        let userId = localStorage.getItem('userId');
        if (this.state.cartQuantity > this.state.quantity) {
            alert("You've selected too many items!");
        } else {
            CartService.addToCart(
                {
                    "id": {
                        "userId": userId,
                        "bookId": this.state.id
                    },
                    "user": {
                        "id": userId
                    },
                    "book": {
                        "id": this.state.id
                    },
                    "quantity": this.state.cartQuantity
                }
            ).then(res => { alert("Product added to your cart") })
        }

    }

    changeQuantityHandler = (event) => {
        this.setState({ cartQuantity: event.target.value });
    }

    render() {
        return (
            <div className="container border p-5">
                <div class="row">

                    <div class="col-md-6">
                        <h1 class="my-3">{this.state.title}</h1>
                        <h5 className="text-muted">Publisher: {this.state.publisher}</h5>
                        <p>{this.state.description}</p>

                    </div>

                    <div class="col-md-3">
                        <h3 class="my-3">Authors:</h3>
                        <ul>
                            {this.state.authors.map(author => {
                                return <li key={author.id}>{author.firstName + " " + author.lastName}</li>
                            })}
                        </ul>
                    </div>

                    <div class="col-md-3">
                        <h3 class="my-3">Categories:</h3>
                        <ul>
                            {this.state.categories.map(category => {
                                return <li key={category.id}>{category.name}</li>
                            })}
                        </ul>
                    </div>
                </div>

                {
                    localStorage.getItem('userId')
                            ? this.state.quantity < 1
                                ? <div>
                                    <button class="btn btn-secondary btn-lg" type="button" disabled>Add to cart</button>
                                    <p className="text-muted">Produt not available</p>
                                </div>
                                : <div>
                                    <div class="input-group col-3 pl-0">
                                        <input type="number" defaultValue="1" class="form-control" onChange={this.changeQuantityHandler} min="1" placeholder="Quantity" />
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" onClick={this.addToCart} type="button">Add to cart</button>
                                        </div>
                                    </div>
                                    <small className="text-muted">{this.state.quantity} items left</small>
                                </div>
                        : <div>
                            <button class="btn btn-secondary btn-lg" type="button" disabled>Add to cart</button>
                            <p className="text-muted">Please login to buy this product</p>
                        </div>
                }


            </div>

        );

    }
}

export default BookDetailsComponent;