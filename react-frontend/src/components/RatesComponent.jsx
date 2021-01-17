import React, { Component } from 'react';
import RateService from '../services/RateService';


class RatesComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            rates: []
        }

        this.changeRate = this.changeRate.bind(this);
        this.saveRates = this.saveRates.bind(this);

    }

    componentDidMount() {
        RateService.getRates().then(res => {
            this.setState({rates: res.data})
        })
    }

    changeRate(e) {
        let newRate = this.state.rates.find(rate => rate.id === e);
        console.log(newRate);
        newRate.rate = document.getElementById('rate'+e).value;

        let rates = [...this.state.rates];
        let rate = {...rates.find(rate => rate.id === e)};
        rate.rate = document.getElementById('rate'+e).value;
        
        this.setState({rates: rates.map(r => r.id === e ? rate : r)});
    }

    saveRates(e) {
        RateService.setRates(this.state.rates).then(res => {
            alert('Success');
        })
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Rates List</h2>
                <div className="row">
                    <table style={{marginTop:"10px"}} className = "table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Currency</th>
                                <th>Symbol</th>
                                <th>Rate</th>
                            </tr>
                        </thead>

                        <tbody>
                            {
                                this.state.rates.map(rate => 
                                    <tr key={rate.id}>
                                    <td>{rate.currency}</td>
                                    <td>{rate.symbol}</td>
                                    <td>
                                    <div class="form-group">
                                        <input onChange={() => this.changeRate(rate.id)} id={'rate' + rate.id} type="number" class="form-control" value={rate.rate}/>
                                    </div>
                                    </td>
                                </tr>
                                )
                            }

                        </tbody>
                    </table>
                    <button type="button" style={{marginLeft: 'auto', marginRight: 'auto'}} className="btn btn-primary btn-lg" onClick={this.saveRates}>Save</button>
                </div>
                
            </div>
        );
    }
}

export default RatesComponent;