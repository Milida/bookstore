import React, { Component } from 'react';
import BookService from '../services/BookService';
import AuthorService from '../services/AuthorService';
import Select from 'react-select'
import PublisherService from '../services/PublisherService';
import CategoryService from '../services/CategoryService';

class UpdateBookComponent extends Component {
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
            authorsList: [],
            categoriesList: [],
            publishersList: [],
            defaultAuthors: []

        }
        this.changeTitleHandler = this.changeTitleHandler.bind(this);
        this.changeAuthorsHandler = this.changeAuthorsHandler.bind(this);
        this.changePublisherHandler = this.changePublisherHandler.bind(this);
        this.changePriceHandler = this.changePriceHandler.bind(this);
        this.changeQuantityHandler = this.changeQuantityHandler.bind(this);
        this.changeCategoryHandler = this.changeCategoryHandler.bind(this);
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
        this.updateBook = this.updateBook.bind(this);
    }

    componentDidMount() {
        BookService.getBookById(this.state.id).then((res) => {
            let book = res.data;
            this.setState({
                title: book.title,
                authors: book.authors,
                publisher: book.publisher,
                categories: book.categories,
                description: book.description,
                price: book.price,
                quantity: book.quantity,
                descriprion: book.descriprion
            });

            AuthorService.getAuthors().then(res => {
                let options = res.data.filter(author => !this.state.authors.includes(author)).map(author => {
                    return { value: { id: author.id, firstName: author.firstName, lastName: author.lastName }, label: author.firstName + " " + author.lastName }
                })
                this.setState({ authorsList: options });
            })
        });



        PublisherService.getPublishers().then(res => {
            let options = res.data.map(publisher => {
                return { value: { id: publisher.id, name: publisher.name }, label: publisher.name }
            })
            this.setState({ publishersList: options });
        })
        CategoryService.getCategories().then(res => {
            let options = res.data.map(category => {
                return { value: { id: category.id, name: category.name }, label: category.name }
            })
            this.setState({ categoriesList: options });
        })

    }
    updateBook = (e) => {
        e.preventDefault();

        let book = {
            title: this.state.title, authors: this.state.authors, categories: this.state.categories, publisher: this.state.publisher,
            price: this.state.price, quantity: this.state.quantity, descriprion: this.state.description
        };
        console.log('book =>' + JSON.stringify(book));

        BookService.updateBook(book, this.state.id).then(res => {
            this.props.history.push('/books');
        }).catch(error => {
            alert("Invalid field values");
        });
    }

    changeTitleHandler = (event) => {
        this.setState({ title: event.target.value });
    }

    changeAuthorsHandler = (event) => {
        if (event) {
            console.log(event);
            this.setState({ authors: event.map(author => author.value) });
        }
    }
    changeCategoryHandler = (event) => {
        if (event) {
            this.setState({ categories: event.map(category => category.value) });
        }
    }

    changePublisherHandler = (event) => {
        if (event) {
            this.setState({ publisher: event.value });
        }
    }

    changePriceHandler = (event) => {
        this.setState({ price: event.target.value });
    }

    changeQuantityHandler = (event) => {
        this.setState({ quantity: event.target.value });
    }

    changeDescriptionHandler = (event) => {
        this.setState({ description: event.target.value });
    }

    cancel() {
        this.props.history.push('/books');
    }

    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Update Book</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Title:</label>
                                        <input placeholder="Title" name="title" className="form-control"
                                            value={this.state.title} onChange={this.changeTitleHandler} />
                                    </div>

                                    <div className="form-group">
                                        <label>Authors:</label>
                                        <Select
                                            className="basic-multi-select"
                                            classNamePrefix="select"
                                            value={this.state.authors.map(a => {
                                                return { value: { id: a.id, firstName: a.firstName, lastName: a.lastName }, label: a.firstName + " " + a.lastName }
                                            })}
                                            isMulti
                                            name="Authors"
                                            options={this.state.authorsList.filter(author => !this.state.authors.includes(author.value))}

                                            onChange={this.changeAuthorsHandler}
                                        />
                                    </div>


                                    <div className="form-group">
                                        <label>Publisher:</label>
                                        <Select onChange={this.changePublisherHandler}  options={this.state.publishersList} />
                                    </div>

                                    <div className="form-group">
                                        <label>Price:</label>
                                        <input placeholder="Price" name="price" className="form-control"
                                            value={this.state.price} onChange={this.changePriceHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Quantity:</label>
                                        <input placeholder="Quantity" name="quantity" className="form-control"
                                            value={this.state.quantity} onChange={this.changeQuantityHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Categories:</label>
                                        <Select
                                            value={this.state.categories.map(c => {
                                                return { value: { id: c.id, name: c.name }, label: c.name  }
                                            })}
                                            isMulti
                                            name="Categories"
                                            options={this.state.categoriesList.filter(category => !this.state.categories.includes(category.value))}

                                            className="basic-multi-select"
                                            classNamePrefix="select"
                                            onChange={this.changeCategoryHandler}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Description:</label>
                                        <textarea rows="5" placeholder="Description" name="description" className="form-control"
                                            value={this.state.descriprion} onChange={this.changeDescriptionHandler} />
                                    </div>
                                    <button className="btn btn-success" onClick={this.updateBook}>Save</button>
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
export default UpdateBookComponent;