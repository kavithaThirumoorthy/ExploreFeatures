import React, { Component } from 'react';
import GroceryDataService from '../services/GroceryDataService';
class GroceryItemSearch extends Component {
    constructor(props) {
      super(props);
      this.state = {  groceryItems: [],
        value: '', hidediv: true};
    
      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }
  
    handleChange(event) {
      this.setState({value: event.target.value,
        hidediv: true});
     
    }
  
    handleSubmit(event) {
        if(this.state.value===""){
            alert("Please select a Category");
        }else{
              
      event.preventDefault();
      this.setState({
        hidediv: false
      });
      GroceryDataService.retrieveCategoryItems(this.state.value)
            .then(
                response => {
                    console.log(response);
                    this.setState({ groceryItems: response.data })
                }
            )
        }
    
    }
  
    render() {
      return (
        <div className="container">
        <form onSubmit={this.handleSubmit}>
          <label>         
         <span style= { {fontWeight:'bold'} } >Search For the Category of items:</span>
         &nbsp; &nbsp;
            <select value={this.state.value} onChange={this.handleChange}>
            <option value="">Select Category</option>
              <option value="Cooking items">Cooking items</option>
              <option value="Meat">Meat</option>
             <option value="Jarred Goods">Jarred Goods</option>
             <option value="Dairy">Dairy</option>
             <option value="Beverages">Beverages</option>
            <option value="Others">Others</option>
            </select>
          </label>
          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
          <input type="submit"   className="btn btn-success" value="Submit" />
        </form>

<div  hidden = {this.state.hidediv}>
<table className="table"  >
    <thead>
        <tr>
            <th>Id</th>
            <th>Item Name</th>
            <th>Quantity</th>
            <th>Category</th>
           
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
                             </tr>
                )
            }
    </tbody>
</table>
</div>


    
</div>
      );
    }
  }

  export default GroceryItemSearch