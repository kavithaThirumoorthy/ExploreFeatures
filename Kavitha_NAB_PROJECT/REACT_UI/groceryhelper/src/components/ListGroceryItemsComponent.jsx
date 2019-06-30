import React, { Component } from 'react';
import GroceryDataService from '../services/GroceryDataService';
import GroceryItemSearch from './GroceryItemSearch';
class ListGroceryItemsComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            groceryItems: [],
            message: null
        }
        this.refreshItems = this.refreshItems.bind(this)
        this.updateGroceryItemClicked = this.updateGroceryItemClicked.bind(this)
        this.deleteGroceryItemClicked = this.deleteGroceryItemClicked.bind(this)
        this.addGroceryItem = this.addGroceryItem.bind(this)
        this.searchGroceryItem = this.searchGroceryItem.bind(this)
    }

    componentDidMount() {
        this.refreshItems();
    }

    refreshItems() {
        GroceryDataService.retrieveAllItems()
            .then(
                response => {
                    console.log(response);
                    this.setState({ groceryItems: response.data })
                }
            )
    }

    updateGroceryItemClicked(id) {
        console.log('update ' + id)
         this.props.history.push(`/groceryItem/${id}`)
    }

    deleteGroceryItemClicked(id) {
        GroceryDataService.deleteGroceryItem(id)
            .then(
                response => {
                    this.setState({ message: `Delete of course ${id} Successful` })
                    this.refreshItems()
                }
            )
    
    }
    addGroceryItem() {
        this.props.history.push(`/groceryItem/-1`)
    }
    searchGroceryItem() {
        this.props.history.push(`/groceryItem/search`)
    }
    render() {
        return (<div className="container">
            
            {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
            <div className="container">
                <table className="table">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Item Name</th>
                            <th>Quantity</th>
                            <th>Category</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                                this.state.groceryItems.map(
                                    groceryItem =>
                                        <tr key={groceryItem.id}>
                                             <td>{groceryItem.id}</td>
                                            <td>{groceryItem.name}</td>
                                            <td>{groceryItem.quantity}</td> 
                                            <td>{groceryItem.category}</td> 
                                            <td><button className="btn btn-success" onClick={() => this.updateGroceryItemClicked(groceryItem.id)}>Update</button></td>
                                            <td><button className="btn btn-warning" 
                                            onClick={() => this.deleteGroceryItemClicked(groceryItem.id)}>Delete</button></td>
                                        </tr>
                                )
                            }
                    </tbody>
                </table>
                <div className="row">
                        <button className="btn btn-success" onClick={this.addGroceryItem}>Add</button>                      
                    </div>
                <GroceryItemSearch></GroceryItemSearch>
                    
            </div>
        </div>);
    }
}
export default ListGroceryItemsComponent;