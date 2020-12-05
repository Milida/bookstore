import React, { Component } from 'react';
import AuthorService from '../services/AuthorService';

class UpdateAuthorComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            firstName: '',
            lastName: ''        
        }
        this.changeFirstnameHandler = this.changeFirstnameHandler.bind(this);
        this.changeLastnameHandler = this.changeLastnameHandler.bind(this);
        this.updateAuthor = this.updateAuthor.bind(this);
    }

    componentDidMount(){
        if (localStorage.getItem('isWorker') !== "true")
            this.props.history.push("/");
        AuthorService.getAuthorById(this.state.id).then((res) =>{
            let author = res.data;
            this.setState({
                firstName : author.firstName,
                lastName: author.lastName
            }); 
        });
    }
    updateAuthor = (e) => {
        e.preventDefault();
        let author = {firstName: this.state.firstName,  lastName: this.state.lastName};
        console.log('author =>' + JSON.stringify(author));
        AuthorService.updateAuthor(author, this.state.id).then(res =>{
            this.props.history.push('/authors');
        });
        
    }

    changeFirstnameHandler = (event) =>{
        this.setState({firstName: event.target.value});
    }
    changeLastnameHandler = (event) =>{
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
                            <h3 className="text-center">Update Author</h3>
                            <div className= "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label>Firstname:</label>
                                        <input placeholder="Firstname" name="firstname" className="form-control" 
                                            value={this.state.firstName} onChange={this.changeFirstnameHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label>Lastname:</label>
                                        <input placeholder="Lastname" name="lastname" className="form-control" 
                                            value={this.state.lastName} onChange={this.changeLastnameHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.updateAuthor}>Save</button>
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
export default UpdateAuthorComponent;