package com.nab.restapi.groceryHelper.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.nab.restapi.groceryHelper.model.GroceryItem;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
	
	@Query(value = "SELECT * FROM GROCERY_ITEM gro WHERE gro.CATEGORY = ?1", nativeQuery = true)
	 List<GroceryItem> getAllCategoryItems(String catgory);
}
