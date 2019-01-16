// This file contains functions related to the REST API requests

// Get the backend API URL from configuration
const API_URL_BASE = process.env.REACT_APP_BACKEND_URL;

// Send request to get all users, then call the callback function
export function restGetUsers(callback) {
    const api_url = API_URL_BASE + "/users";
    fetch(api_url)
        .then(response => {
            return response.json();
        }).then(callback);
}

// Send request to delete a user, then call the callback function
export function restDeleteUser(email, callback) {
    const api_url = API_URL_BASE + "/deleteUser?email=" + email;
    fetch(api_url, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(callback);
}

// Send request to get all users, then call the callback function
export function restCreateUser(user, callback) {
    const api_url = process.env.REACT_APP_BACKEND_URL + "/createUser";
    fetch(api_url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    }).then(callback);
}