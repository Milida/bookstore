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
                    <span className="text-muted"> Made By: Ida Milewska, Michał Sawicki, Natalia Tarasiuk</span>
                </footer>
                
            </div>
        );
    }
}

export default FooterComponent;