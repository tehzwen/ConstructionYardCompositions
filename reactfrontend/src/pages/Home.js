import React, { Component } from 'react';
import Axios from 'axios';

export default class Home extends Component {
    constructor(props) {
        super(props);
    }

    //test function hitting the backend
    hitBackendReports() {
        Axios.get("http://localhost:4500/reports")
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                console.error(err);
            });
    }

    render() {
        return (
            <div>
                <button onClick={this.hitBackendReports}>Click me :)</button>
            </div>
        )
    }
}