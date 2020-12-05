import React, { Component } from 'react';
import BookService from '../../services/BookService';


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
            });
        });
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
                        ? <div><div class="input-group col-3 pl-0">
                            <input type="number" class="form-control" placeholder="Quantity" />
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">Add to cart</button>
                            </div>
                        </div>
                        <small className="text-muted">{this.state.quantity} items left</small></div>
                        : <div>
                            <button class="btn btn-secondary btn-lg disabled" type="button">Add to cart</button>
                            <p className="text-muted">Please login to buy this product</p>
                        </div>
                }


            </div>

        );

    }
}

export default BookDetailsComponent;