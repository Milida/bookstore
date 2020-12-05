import React, { Component } from 'react';
import PublisherService from '../services/PublisherService';

class AddPublisherComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            name: '',         
        }
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.savePublisher = this.savePublisher.bind(this);
    }
    
    savePublisher = (e) => {
        e.preventDefault();
        let publisher = {name: this.state.name};
        if (!(publisher.name)) {
            alert("Please fill the field");
        } else {
            
            console.log('publisher =>' + JSON.stringify(publisher));
    
            PublisherService.addPublisher(publisher).then(res =>{
                this.props.history.push('/publishers');
            }).catch(error => alert("This Publisher already exists!"));
        }

    }

    componentDidMount() {
        if (localStorage.getItem('isWorker') !== "true")
            this.props.history.push("/");
    }

    changeNameHandler = (event) =>{
        this.setState({name: event.target.value});
    }

    cancel(){
        this.props.history.push('/publishers');
    }

    render() {
        return (
            <div>
                <div className = "container">
                    <div className = "row">
                        <div className = "card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Update Publisher</h3>
                            <div className= "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label>Name:</label>
                                        <input placeholder="Name" name="name" className="form-control" 
                                            value={this.state.name} onChange={this.changeNameHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.savePublisher}>Save</button>
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
export default AddPublisherComponent;