import React from 'react';
import ReactDOM from 'react-dom';
import './css/index.css';
import Header from './containers/Header';
import Table from './containers/Table';
import * as serviceWorker from './serviceWorker';

function renderReactComponent(component, element) {
    ReactDOM.render(React.createElement(component), document.getElementById(element));
}

renderReactComponent(Header, 'header');
renderReactComponent(Table, 'table');

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();

