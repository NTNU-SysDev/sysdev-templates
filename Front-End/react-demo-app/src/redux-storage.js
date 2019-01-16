import { createStore } from 'redux';

/////////////////////////////////////////////////
// Redux stuff
/////////////////////////////////////////////////

// This file contains Redux related things
// For documentation on Redux read: https://redux.js.org/

// Idea in short: Redux maintains a storage of data loaded in the browser. Any component can send update actions
// to the store. And Redux updates the store state. On every update, Redux notifies all the observers.
// React components can be observers and thus update their state.
// There is an official React+Redux library. https://github.com/reduxjs/react-redux
// However, it is rather complicated. This example does plain Redux handling

// Actions for the Reducer
export function addUser(user) {
    return {
        type: "ADD",
        user: user
    }
}

export function removeUser(email) {
    return {
        type: "REMOVE",
        email: email
    }
}

export function reloadUsers(users) {
    return {
        type: "RELOAD",
        users: users
    }
}


/**
 * Get new state based on previous state and performed action
 * Must NOT modify the existing (old) state
 * @param state
 * @param action
 * @returns {*}
 */
function userStorageReducer(state = [], action) {
    switch (action.type) {
        case "ADD":
            return state.concat(action.user);
        case "REMOVE":
            return state.filter(function (item) {
                return item.email !== action.email;
            });
        case "RELOAD":
            // Replace all the current users with the new user list
            return action.users;
        default:
            return state;
    }
}

// This is the central Redux storage. All the components should use it to either update storage state, or
// get a notification about state updates
export var reduxStore = createStore(userStorageReducer);