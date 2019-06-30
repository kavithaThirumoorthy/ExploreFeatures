import axios from 'axios'


const GROCERY_HELPER_REST_API_URL_BASE = 'http://localhost:8080'
const GROCERY_HELPER_REST_API_URL = `${GROCERY_HELPER_REST_API_URL_BASE}/groceryItem`
const GROCERY_HELPER_CATEGORY_REST_API_URL = `${GROCERY_HELPER_REST_API_URL_BASE}/groceryItem/category`
class GroceryDataService {

    retrieveAllItems() {
        return axios.get(`${GROCERY_HELPER_REST_API_URL}`);
    }
    deleteGroceryItem(id) {
       
        return axios.delete(`${GROCERY_HELPER_REST_API_URL}/${id}`);
    }

    retrieveGroceryItem(id) {
       
          console.log(id)
        return axios.get(`${GROCERY_HELPER_REST_API_URL}/${id}`);
      
    }
    updateGroceryItem( id,groceryItem) {
      
        console.log(id)

        return axios.put(`${GROCERY_HELPER_REST_API_URL}/${id}`, groceryItem);
    }

    createGroceryItem(groceryItem) {

        console.log(groceryItem)
         return axios.post(`${GROCERY_HELPER_REST_API_URL}`, groceryItem);
    }
    retrieveCategoryItems(categoryName) {
      
        console.log(categoryName)
         return axios.get(`${GROCERY_HELPER_CATEGORY_REST_API_URL}/${categoryName}`, categoryName);
    }
}

export default new GroceryDataService()
