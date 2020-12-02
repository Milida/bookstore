import React, { Component } from 'react';
import BookService from '../services/BookService';

class ListBookComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            books : []
        }
        this.addBook = this.addBook.bind(this);
    }

    componentDidMount(){
        BookService.getBooks().then((res)  => {
            this.setState({books: res.data});
        });
    }

    addBook(){
        this.props.history.push('/add-book');
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Book List</h2>
                <div className = "row">
                    <button className="btn btn-primary" onClick={this.addBook}>Add Book</button>
                </div>
                <div className="row">
                    <table className = "table table-striped table-bordered">
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
                                    <tr key = {book.id}>
                                        <td>{book.title}</td>
                                        <td>{book.authors[0].firstName+ " " + book.authors[0].lastName } </td>
                                        <td>{book.publisher.name}</td>
                                        <td>{book.price}</td>
                                        <td>{book.quantity}</td>
                                        <td>{book.categories[0].name}</td>
                                        <td>Action</td>
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