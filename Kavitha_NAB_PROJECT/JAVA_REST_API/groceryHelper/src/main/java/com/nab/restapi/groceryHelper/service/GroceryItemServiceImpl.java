package com.nab.restapi.groceryHelper.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nab.restapi.groceryHelper.model.GroceryItem;
import com.nab.restapi.groceryHelper.repository.GroceryItemRepository;

@Service

public class GroceryItemServiceImpl implements GroceryItemService {

	@Autowired
    private GroceryItemRepository groceryItemRepository;

  
    @Override
    public  List<GroceryItem> getAllItems() {
        return groceryItemRepository.findAll();
    }

  
    @Override
    public GroceryItem save(GroceryItem item) {
    	return groceryItemRepository.save(item);
    }

    @Override
    public void deleteById(Long id) {
    	groceryItemRepository.deleteById(id);
    }

	@Override
	public Optional<GroceryItem> getItem(long id) {
		return groceryItemRepository.findById(id);
	}


	@Override
	public List<GroceryItem> getAllCategoryItems(String catgory) {
		return groceryItemRepository.getAllCategoryItems(catgory);
	}

}
