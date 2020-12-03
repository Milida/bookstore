import React, { Component } from 'react';

class HeaderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            
        }
    }

    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark text-white">
                        <div> <a href = "http://localhost:3000" className="navbar-brand" >Book Shop</a></div>
                        <div><a href = "http://localhost:3000/books" className="navbar-brand" >Books</a></div>
                        <div><a href = "http://localhost:3000/publishers" className="navbar-brand" >Publishers</a></div>
                        <div><a href = "http://localhost:3000/authors" className="navbar-brand" >Authors</a></div>
                        <div><a href = "http://localhost:3000/categories" className="navbar-brand" >Categories</a></div>
                        <div><a href = "http://localhost:3000/users" className="navbar-brand" >Users</a></div>
                    </nav>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;