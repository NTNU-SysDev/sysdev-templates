import React from 'react';
import {restDeleteUser} from "../rest-api";
import {reduxStore, removeUser} from "../redux-storage";

class Row extends React.Component {

    constructor() {
        super();
        // Need to manually bind component methods so that we can access 'this' within them
        this.deleteExistingUser = this.deleteExistingUser.bind(this);
    }

    deleteExistingUser() {
        // Send REST request to the backend, ask to delete the user. On success, notify Redux store
        // This will in turn generate an event and Table will be notified - it will refresh user list
        const email = this.props.email;
        restDeleteUser(email, function() {
            reduxStore.dispatch(removeUser(email));
        })
    }

    render() {
        return (
            <tr>
                <td>{this.props.name}</td>
                <td>{this.props.email}</td>
                <td>{this.props.phone}</td>
                <td>{this.props.age}</td>
                <td>
                    <button onClick={this.deleteExistingUser} className="red waves-effect waves-light btn">
                        Delete
                    </button>
                </td>
            </tr>);
    }
}

export default Row;