package com.nab.restapi.groceryHelper.service;


import org.springframework.validation.annotation.Validated;

import com.nab.restapi.groceryHelper.model.GroceryItem;

import java.util.List;
import java.util.Optional;

@Validated
public interface GroceryItemService {

    List<GroceryItem> getAllItems();

    Optional<GroceryItem> getItem(long id);

    GroceryItem save(GroceryItem item);

    void deleteById(Long id);
    List<GroceryItem> getAllCategoryItems(String catgory);
}
