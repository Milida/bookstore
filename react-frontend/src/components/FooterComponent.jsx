import React, { Component } from 'react';

class FooterComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            
        }
    }
    
    render() {
        return (
            <div>
                <footer className="footer">
                    Project created by: Ida Milewska, Michał Sawicki, Natalia Tarasiuk
                </footer>
                
            </div>
        );
    }
}

export default FooterComponent;