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
        this.removeCategory = this.removeCategory.bind(this);
    }
    
    editCategory(category){
        this.props.history.push(`/update-category/${category.id}`);
    }

    componentDidMount(){
        if (localStorage.getItem('isWorker') !== "true")
            this.props.history.push("/");
        CategoryService.getCategories().then((res)  => {
            this.setState({categories: res.data});
        });
    }
    addCategory(){
        this.props.history.push('/add-category');
    }
    removeCategory(categoryToRemove) {
        CategoryService.removeCategory(categoryToRemove).then(res =>
            this.setState({ categories: this.state.categories.filter(category => category.id !== categoryToRemove.id) })).catch(error => alert("Cannot remove category with assigned books!"));
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Categories List</h2>
                <div className = "row">
                    <button className="btn btn-primary" onClick={this.addCategory}>Add Category</button>
                </div>
                <div className="row">
                    <table style={{marginTop:"10px"}} className = "table table-striped table-bordered">
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
                                            <button style={{marginRight:"10px"}} onClick ={() => this.editCategory(category)} className="btn btn-info">Update</button>
                                            <button onClick={() => this.removeCategory(category)} className="btn btn-danger">Delete</button>
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