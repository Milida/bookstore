import React, { Component } from 'react';
import CategoryService from '../services/CategoryService';

class ListCategoriesComponent extends Component {
    constructor(props) {
        super(props)

        this.state ={
            categories : []
        }
        this.addCategory = this.addCategory.bind(this);
        this.editCategory = this.editCategory.bind(this);
    }
    
    editCategory(category){
        this.props.history.push(`/update-category/${category.id}`);
    }

    componentDidMount(){
        CategoryService.getCategories().then((res)  => {
            this.setState({categories: res.data});
        });
    }
    addCategory(){
        this.props.history.push('/add-category');
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Categories List</h2>
                <div className = "row">
                    <button className="btn btn-primary" onClick={this.addCategory}>Add Category</button>
                </div>
                <div className="row">
                    <table className = "table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Categories Name</th>
                                <th>Description</th>
                                <th>Actions</th>
                            </tr>
                        </thead>

                        <tbody>
                            {
                                this.state.categories.map(
                                    category =>
                                    <tr key = {category.id}>
                                        <td>{category.name}</td>
                                        <td>{category.description}</td>
                                        <td>
                                            <button onClick ={() => this.editCategory(category)} className="btn btn-info">Update</button>
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

export default ListCategoriesComponent;