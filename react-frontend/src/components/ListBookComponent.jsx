import React, { Component } from 'react';
import BookService from '../services/BookService';

class ListBookComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            books: []
        }
        this.addBook = this.addBook.bind(this);
        this.removeBook = this.removeBook.bind(this);

    }

    componentDidMount() {
        BookService.getBooks().then((res) => {
            this.setState({ books: res.data });
        });
    }

    addBook() {
        this.props.history.push('/add-book');
    }

    removeBook(bookToRemove) {
        BookService.removeBook(bookToRemove).then(res =>
            this.setState({ books: this.state.books.filter(book => book.id !== bookToRemove.id) }));
    }


    render() {
        return (
            <div>
                <h2 className="text-center">Book List</h2>
                <div className="row">
                    <button className="btn btn-primary" onClick={this.addBook}>Add Book</button>
                </div>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                {/* <th>ID</th> */}
                                <th>Title</th>
                                <th>Authors</th>
                                <th>Publisher</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Category</th>
                                <th>Actions</th>
                            </tr>
                        </thead>

                        <tbody>
                            {
                                this.state.books.map(
                                    book =>
                                        <tr key={book.id}>
                                            <td>{book.title}</td>
                                            <td><ul>{book.authors.map(author => <li key={author.id}>{author.firstName + " " + author.lastName}</li>)} </ul></td>
                                            <td>{book.publisher.name}</td>
                                            <td>{book.price}</td>
                                            <td>{book.quantity}</td>
                                            <td><ul>{book.categories.map(category => <li key={category.id}>{category.name}</li>)}</ul></td>
                                            <td>
                                                <button onClick={() => this.removeBook(book)} className="btn btn-danger">Delete</button>
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

export default ListBookComponent;