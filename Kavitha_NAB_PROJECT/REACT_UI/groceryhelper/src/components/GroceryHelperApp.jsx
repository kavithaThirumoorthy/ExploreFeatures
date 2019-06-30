import React, { Component } from 'react';
import ListGroceryItemsComponent from './ListGroceryItemsComponent';
import GroceryItemComponent from './GroceryItemComponent';

import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
class GroceryHelperApp extends Component {
    render() {
        return (
            <Router>
                <h1>GroceryHelper Application</h1>
               
              <Switch>
                        <Route path="/" exact component={ListGroceryItemsComponent} />
                        <Route path="/groceryItem" exact component={ListGroceryItemsComponent} />
                        <Route path="/groceryItem/:id" component={GroceryItemComponent} />
                   </Switch>
                    </Router>
        )
    }
}

export default GroceryHelperApp;