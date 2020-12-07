import React, { Component } from 'react';

class FooterComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {

        }
    }

    render() {
        return (
            <footer className="footer bg-dark text-white">
                Project created by: Ida Milewska, Michał Sawicki, Natalia Tarasiuk
            </footer>
        );
    }
}

export default FooterComponent;