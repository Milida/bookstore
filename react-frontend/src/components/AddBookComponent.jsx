import React, { Component } from 'react';
import BookService from '../services/BookService';

class AddBookComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            title: '',
            authors: '',
            publisher: '',
            price: '',
            quantity: '',
            categories: '',
            description:''
            
        }
        this.changeTitleHandler = this.changeTitleHandler.bind(this);
        //this.changeAuthorsHandler = this.changeAuthorsHandler.bind(this);
        //this.changePublisherHandler = this.changePublisherHandler.bind(this);
        this.changePriceHandler = this.changePriceHandler.bind(this);
        this.changeQuantityHandler = this.changeQuantityHandler.bind(this);
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
        this.saveBook = this.saveBook.bind(this);
    }
    
    saveBook = (e) => {
        e.preventDefault();
        let book = {title: this.state.title, authors: this.state.authors, publisher: this.state.publisher,
                     price: this.state.price, quantity: this.state.quantity, descriprion: this.state.description};
        console.log('book =>' + JSON.stringify(book));

        BookService.addBook(book).then(res =>{
            this.props.history.push('/books');
        });
    }

    changeTitleHandler = (event) =>{
        this.setState({title: event.target.value});
    }

    // changeAuthorsHandler = (event) =>{
    //     this.setState({authors: event.target.value});
    // }

    // changePublisherHandler = (event) =>{
    //     this.setState({publisher: event.target.value});
    // }
    changePriceHandler = (event) =>{
        this.setState({price: event.target.value});
    }

    changeQuantityHandler = (event) =>{
        this.setState({quantity: event.target.value});
    }

    changeDescriptionHandler = (event) =>{
        this.setState({description: event.target.value});
    }

    cancel(){
        this.props.history.push('/books');
    }

    render() {
        return (
            <div>
                <div className = "container">
                    <div className = "row">
                        <div className = "card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add Book</h3>
                            <div className= "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label>Title:</label>
                                        <input placeholder="Title" name="title" className="form-control" 
                                            value={this.state.title} onChange={this.changeTitleHandler}/>
                                    </div>
                                    {/* <div className = "form-group">
                                        <label>Authors:</label>
                                        <input placeholder="Author's firstname" name="authorsName" className="form-control" 
                                            value={this.state.authors} onChange={this.changeAuthorsHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label>Publisher:</label>
                                        <input placeholder="Publisher" name="publisher" className="form-control" 
                                            value={this.state.publisher} onChange={this.changePublisherHandler}/>
                                    </div> */}
                                    {/* <div class="form-group">
                                        <label for="authors">State</label>
                                        <select id="inputState" class="form-control">

                                         <option selected>Choose...</option>
                                        <option>...</option>
                                        </select>
                                     </div> */}
                                    <div className = "form-group">
                                        <label>Price:</label>
                                        <input placeholder="Price" name="price" className="form-control" 
                                            value={this.state.price} onChange={this.changePriceHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label>Quantity:</label>
                                        <input placeholder="Quantity" name="quantity" className="form-control" 
                                            value={this.state.quantity} onChange={this.changeQuantityHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label>Description:</label>
                                        <input placeholder="Description" name="description" className="form-control" 
                                            value={this.state.descriprion} onChange={this.changeDescriptionHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.saveBook}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>           
                        </div>                   
                    </div>
                </div>    
            </div>
        );
    }
}

export default AddBookComponent;