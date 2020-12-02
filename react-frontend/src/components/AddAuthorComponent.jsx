import React, { Component } from 'react';
import AuthorService from '../services/AuthorService';

class AddAuthorComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            firstName: '',
            lastName:''           
        }
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.saveAuthor = this.saveAuthor.bind(this);
    }
    
    saveAuthor = (e) => {
        e.preventDefault();
        let author = {firstName: this.state.firstName,  lastName: this.state.lastName};
        if (!(author.firstName && author.lastName)) {
            alert("Please fill all the fields");
        } else {
            
            console.log('author =>' + JSON.stringify(author));
    
            AuthorService.addAuthor(author).then(res =>{
                this.props.history.push('/authors');
            }).catch(error => alert("This author already exists!"));;
        }

    }

    changeFirstNameHandler = (event) =>{
        this.setState({firstName: event.target.value});
    }
    changeLastNameHandler = (event) =>{
        this.setState({lastName: event.target.value});
    }

    cancel(){
        this.props.history.push('/authors');
    }

    render() {
        return (
            <div>
                <div className = "container">
                    <div className = "row">
                        <div className = "card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add Author</h3>
                            <div className= "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label>First Name:</label>
                                        <input placeholder="Firstname" name="firstname" className="form-control" 
                                            value={this.state.name} onChange={this.changeFirstNameHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label>Last Name:</label>
                                        <input placeholder="Lastname" name="lastName" className="form-control" 
                                            value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.saveAuthor}>Save</button>
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

export default AddAuthorComponent;