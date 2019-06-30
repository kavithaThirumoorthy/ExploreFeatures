package com.nab.restapi.groceryHelper.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nab.restapi.groceryHelper.model.GroceryItem;
import com.nab.restapi.groceryHelper.service.GroceryItemService;
@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class GroceryItemController {
	
	@RequestMapping (value="/")
	public String hello(){
		return "Hello world";
		
	}

	
	@Autowired
	private GroceryItemService groceryItemService;

	@GetMapping("/groceryItem")
	public List<GroceryItem> retrieveAll() {
		return groceryItemService.getAllItems();
	}

	@GetMapping("/groceryItem/{id}")
	public GroceryItem retrieveGroceryItem(@PathVariable long id) {
	Optional<GroceryItem> groceryItem = groceryItemService.getItem(id);
	return groceryItem.get();
	}

	@DeleteMapping("/groceryItem/{id}")
	public void deleteGroceryItem(@PathVariable long id) {
		groceryItemService.deleteById(id);
	}

	@PostMapping("/groceryItem")
	public ResponseEntity<Object>  createGroceryItem(@RequestBody GroceryItem groceryItem) {
		 groceryItemService.save(groceryItem);
		return ResponseEntity.noContent().build();
	}
	@PutMapping("/groceryItem/{id}")
	public ResponseEntity<Object> updateGroceryItem(@RequestBody GroceryItem groceryItem, @PathVariable long id) {

		Optional<GroceryItem> groceryItemOptional = groceryItemService.getItem(id);		

		GroceryItem groceryItemt=groceryItemOptional.get();
		groceryItemt.setName(groceryItem.getName());
		groceryItemt.setQuantity(groceryItem.getQuantity());
		groceryItemt.setCategory(groceryItem.getCategory());
		groceryItemService.save(groceryItemt);
		return ResponseEntity.noContent().build();
	}
	@GetMapping("/groceryItem/category/{categoryName}")
	public List<GroceryItem> retrieveCategoryItems(@PathVariable String categoryName) {
		return groceryItemService.getAllCategoryItems(categoryName);
	 
	}
}
