package com.nab.restapi.groceryHelper.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import com.nab.restapi.groceryHelper.model.GroceryItem;
import com.nab.restapi.groceryHelper.repository.GroceryItemRepository;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GroceryItemServiceImplTest {
	@InjectMocks
	GroceryItemServiceImpl groceryItemService;
	@Mock
	private GroceryItemRepository groceryItemRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void test_GetAllItems() {
		GroceryItem groceryItem = new GroceryItem();
		groceryItem.setId((long) 2);
		groceryItem.setName("Rice");
		groceryItem.setQuantity((int) 45);
		groceryItem.setCategory("Cooking");

		List<GroceryItem> groceryItems = Arrays.asList(groceryItem);
		when(groceryItemRepository.findAll()).thenReturn(groceryItems);
		List<GroceryItem> groceryItemTemp = groceryItemService.getAllItems();
		assertThat(groceryItems, is(groceryItemTemp));
	}

	@Test
	public void test_save() {
		GroceryItem groceryItem = new GroceryItem();
		groceryItem.setId((long) 2);
		groceryItem.setName("Rice");
		groceryItem.setQuantity((int) 45);
		groceryItem.setCategory("Cooking");
		groceryItemService.save(groceryItem);
		verify(groceryItemRepository, times(1)).save(groceryItem);

	}

	@Test
	public void test_deleteById() {
		groceryItemService.deleteById((long) 45);
		verify(groceryItemRepository, times(1)).deleteById((long) 45);

	}

	@Test
	public void test_getItem() {
		groceryItemService.getItem((long) 45);
		verify(groceryItemRepository, times(1)).findById((long) 45);

	}
	@Test
	public void test_getAllCategoryItems() {
		GroceryItem groceryItem = new GroceryItem();
		groceryItem.setId((long) 2);
		groceryItem.setName("Rice");
		groceryItem.setQuantity((int) 45);
		groceryItem.setCategory("Cooking");

		List<GroceryItem> groceryItems = Arrays.asList(groceryItem);
		when(groceryItemRepository.getAllCategoryItems("Cooking")).thenReturn(groceryItems);
		List<GroceryItem> groceryItemTemp = groceryItemService.getAllCategoryItems("Cooking");
		assertThat(groceryItems, is(groceryItemTemp));
	}
}
