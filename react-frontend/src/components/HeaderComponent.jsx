import React, { Component } from 'react';
import {Link} from 'react-router-dom';

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
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark text-white mb-4">
                        <div><Link className="navbar-brand" to = "/">Book Shop</Link></div>
                        <div><Link className="navbar-brand" to = "/books">Books</Link></div>
                        <div><Link className="navbar-brand" to = "/publishers">Publishers</Link></div>
                        <div><Link className="navbar-brand" to = "/authors">Authors</Link></div>
                        <div><Link className="navbar-brand" to = "/categories">Categories</Link></div>
                        <div><Link className="navbar-brand" to = "/users">Users</Link></div>
                    </nav>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;