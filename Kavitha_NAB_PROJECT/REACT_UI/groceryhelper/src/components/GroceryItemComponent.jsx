import React, { Component } from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import GroceryDataService from '../services/GroceryDataService';
class GroceryItemComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            name: '',
            quantity:'',
            category:''
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)

    }


    componentDidMount() {

        console.log(this.state.id)

       if (this.state.id === '-1') {
            return
        }

        GroceryDataService.retrieveGroceryItem( this.state.id)
            .then(response => {
                console.log(response.data.name);
                console.log(response.data.quantity);
                this.setState({
                name: response.data.name,
                quantity :response.data.quantity,
                category:response.data.category
            })
           
        })
    }
    validate(values) {
        let errors = {}
        if (!values.name) {
            errors.name = 'Enter a name'
        } else if (!values.quantity) {
            errors.quantity = 'Enter a quantity'
        }

        return errors

    }
   

  onSubmit(values) {
 
    let groceryItem = {
        id: this.state.id,
        name: values.name,
        quantity: values.quantity,
        category:values.category
    }
    console.log(values);
    console.log(groceryItem.id);
    console.log(groceryItem.category);

    if (this.state.id === '-1') {
        GroceryDataService.createGroceryItem(groceryItem)
            .then(() => this.props.history.push('/groceryItem'))
    } else {
        GroceryDataService.updateGroceryItem( groceryItem.id, groceryItem)
           .then(() => this.props.history.push('/groceryItem'))
    }

    console.log(values);
}

render() {


    return (
        <div>
            <h3>Grocery Item</h3>
            <div className="container">
                <Formik
                    initialValues=''
                    onSubmit={this.onSubmit}
                    validateOnChange={false}
                    validateOnBlur={false}
                    validate={this.validate}
                    enableReinitialize={true}
                >
                    {
                        (props) => (
                            <Form>
                                <ErrorMessage name="name" component="div"
                                    className="alert alert-warning" />
                                <ErrorMessage name="quantity" component="div"
                                    className="alert alert-warning" />
                                <fieldset className="form-group">
                                    <label>Id</label>
                                    <Field className="form-control" type="text" name="id" value={this.state.id} disabled />
                                </fieldset>
                                <fieldset className="form-group">
                                    <label>Name</label>
                                    <Field className="form-control" type="text" name="name"  />
                                </fieldset>
                                <fieldset className="form-group">
                                    <label>Quantity</label>
                                    <Field className="form-control" type="text" name="quantity"  />
                                </fieldset>
                                <fieldset className="form-group">
                                    <label>Category</label>
                                    <Field className="form-control"  component="select" name="category">
                                    <option value="">None</option>
                                        <option value="Cooking items">Cooking items</option>
                                         <option value="Meat">Meat</option>
                                         <option value="Jarred Goods">Jarred Goods</option>
                                         <option value="Dairy">Dairy</option>
                                         <option value="Beverages">Beverages</option>
                                         <option value="Others">Others</option>
                                     </Field>
                                </fieldset>
                               
                                <button className="btn btn-success" type="submit">Save</button>
                            </Form>
                        )
                    }
                </Formik>

            </div>
        </div>
    )
}


}

export default GroceryItemComponent