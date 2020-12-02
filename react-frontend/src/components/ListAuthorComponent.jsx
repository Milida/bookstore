import React, { Component } from 'react';
import AuthorService from '../services/AuthorService';

class ListAuthorComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            authors : []
        }
        this.addAuthor = this.addAuthor.bind(this);
        this.editAuthor = this.editAuthor.bind(this);
    }

    editAuthor(author){
        this.props.history.push(`/update-author/${author.id}`);
    }

    componentDidMount(){
        AuthorService.getAuthors().then((res)  => {
            this.setState({authors: res.data});
        });
    }
    addAuthor(){
        this.props.history.push('/add-author');
     }

    render() {
        return (
            <div>
                <h2 className="text-center">Authors List</h2>
                <div className = "row">
                    <button className="btn btn-primary" onClick={this.addAuthor}>Add Author</button>
                </div>
                <div className="row">
                    <table className = "table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Author's Firstname</th>
                                <th>Author's Lastname</th>

                                <th>Actions</th>
                            </tr>
                        </thead>

                        <tbody>
                            {
                                this.state.authors.map(
                                    author =>
                                    <tr key = {author.id}>
                                        <td>{author.firstName}</td>
                                        <td>{author.lastName}</td>
                                        <td>
                                            <button onClick ={() => this.editAuthor(author)} className="btn btn-info">Update</button>
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

export default ListAuthorComponent;