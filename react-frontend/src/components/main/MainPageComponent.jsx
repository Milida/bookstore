import React, { Component } from 'react';
import BookService from '../../services/BookService';
import BookCard from './BookCardComponent';

class MainPageComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            books: []
        }
    }

    componentDidMount() {
        BookService.getBooks().then(res => {
            this.setState({ books: res.data });
        })
    }


    render() {
        return (
            <div className="container">
                <div className="row justify-content-center">

                    {this.state.books.map(book => {
                        return <BookCard key={book.id} book={book} />
                    })}



                </div>
            </div>
        )
    }
}

export default MainPageComponent;