import React, { Component } from 'react';
import {Link} from 'react-router-dom';

class BookCardComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            book: props.book
        }
    }


    render() {
        return (
            <div className="col-auto mb-3">
                <div className="card" style={{ width: 20 + 'rem', height: 20 + 'rem' }}>
                    <div className="card-body">
                        <h5 className="card-title"> <Link to={`books/${this.state.book.id}`}>{this.state.book.title}</Link></h5>
                        <h6 className="card-subtitle mb-2">{this.state.book.price + ' ZŁ'}</h6>
                        {/* <p className="card-text text-justify card-text-limited">  {this.state.book.description && this.state.book.description.length > 265 ?
                            `${this.state.book.description.substring(0, 265)}...` : this.state.book.description
                        }</p> */}
                        <p className="card-text text-justify card-text-limited"> {this.state.book.description} </p>
                    </div>
                    <div className="card-footer text-center">
                        <a href="#" className="card-link">Add to cart</a>
                    </div>
                </div>
            </div>
        )
    }
}

export default BookCardComponent;