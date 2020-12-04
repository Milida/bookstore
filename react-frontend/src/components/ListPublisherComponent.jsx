import React, { Component } from 'react';
import PublisherService from '../services/PublisherService';

class ListPublisherComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            publishers : []
        }
        this.addPublisher = this.addPublisher.bind(this);
        this.editPublisher = this.editPublisher.bind(this);
        this.removePublisher = this.removePublisher.bind(this);
    }

    editPublisher(publisher){
        this.props.history.push(`/update-publisher/${publisher.id}`);
    }

    componentDidMount(){
        PublisherService.getPublishers().then((res)  => {
            this.setState({publishers: res.data});
        });
    }
    addPublisher(){
        this.props.history.push('/add-publisher');
     }
     removePublisher(publisherToRemove) {
        PublisherService.removePublisher(publisherToRemove).then(res =>
            this.setState({ publishers: this.state.publishers.filter(publisher => publisher.id !== publisherToRemove.id) })).catch(error => alert("Cannot remove publisher with assigned books!"));
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Publishers List</h2>
                <div className = "row">
                    <button className="btn btn-primary" onClick={this.addPublisher}>Add Publisher</button>
                </div>
                <div className="row">
                    <table style={{marginTop:"10px"}} className = "table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Publisher's Name</th>
                                <th>Actions</th>
                            </tr>
                        </thead>

                        <tbody>
                            {
                                this.state.publishers.map(
                                    publisher =>
                                    <tr key = {publisher.id}>
                                        <td>{publisher.name}</td>
                                        <td>
                                            <button style={{marginRight:"10px"}} onClick ={() => this.editPublisher(publisher)} className="btn btn-info">Update</button>
                                            <button onClick={() => this.removePublisher(publisher)} className="btn btn-danger">Delete</button>
                                        
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

export default ListPublisherComponent;