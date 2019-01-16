import React from "react";
import Row from '../components/Row';
import {reduxStore, reloadUsers} from '../redux-storage'
import {restGetUsers} from '../rest-api'

class Table extends React.Component {

    constructor() {
        super();
        this.state = {
            users: [],
        };
        // Need to manually bind component methods so that we can access 'this' within them
        this.onUserListUpdated = this.onUserListUpdated.bind(this);
    }

    componentDidMount() {
        // Send REST request to get users, notify Redux store when response arrives
        restGetUsers(function(json_response) {
            reduxStore.dispatch(reloadUsers(json_response));
        });

        // Subscribe to notification whenever Redux store is updated
        reduxStore.subscribe(this.onUserListUpdated);
    }

    onUserListUpdated() {
        // This method is called whenever Redux store state is updated
        // Update our internal state accordingly
        var reduxState = reduxStore.getState();
        this.setState({users: reduxState});
    }

    render() {
        // Generate a Row component from each user object in the current state
        let users = this.state.users.map((row) => {
            return <Row key={row.email} name={row.name} email={row.email}
                        phone={row.phone} age={row.age}/>
        });

        return (
            <table className="highlight centered">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Age</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                {users}
                </tbody>
            </table>
        );
    }
}

export default Table;