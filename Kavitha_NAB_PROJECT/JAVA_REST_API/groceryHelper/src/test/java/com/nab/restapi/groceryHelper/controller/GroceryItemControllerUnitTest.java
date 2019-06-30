package com.nab.restapi.groceryHelper.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nab.restapi.groceryHelper.model.GroceryItem;
import com.nab.restapi.groceryHelper.service.GroceryItemService;

public class GroceryItemControllerUnitTest {

	@InjectMocks
	private GroceryItemController groceryItemController;

	@Mock
	GroceryItemService groceryItemService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(groceryItemController).build();
	}

	@Test
	public void testMainPage() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	public void test_RetrieveAll() throws Exception {
		GroceryItem groceryItem = new GroceryItem();
		groceryItem.setId((long) 2);
		groceryItem.setName("Rice");
		groceryItem.setQuantity((int) 45);
		groceryItem.setCategory("Cooking");

		List<GroceryItem> groceryItems = Arrays.asList(groceryItem);
		when(groceryItemService.getAllItems()).thenReturn(groceryItems);
		this.mockMvc.perform(get("/groceryItem")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
		;
	}

	@Test
	public void test_RetrieveGroceryItem() throws Exception {
		GroceryItem groceryItem = new GroceryItem();
		groceryItem.setId((long) 2);
		groceryItem.setName("Rice");
		groceryItem.setQuantity((int) 45);
		groceryItem.setCategory("Cooking");
		Optional<GroceryItem> opt = Optional.of(groceryItem);
		when(groceryItemService.getItem(45)).thenReturn(opt);
		this.mockMvc.perform(get("/groceryItem/45")).andExpect(status().isOk());
	}

	@Test
	public void test_DeleteGroceryItem() throws Exception {
		this.mockMvc.perform(delete("/groceryItem/45")).andExpect(status().isOk());
	}

	@Test
	public void test_CreateGroceryItem() throws Exception {
		GroceryItem groceryItem = new GroceryItem();
		groceryItem.setId((long) 2);
		groceryItem.setName("Rice");
		groceryItem.setQuantity((int) 45);
		groceryItem.setCategory("Cooking");
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonContent = mapper.writeValueAsString(groceryItem);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/groceryItem").content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

	}

	@Test
	public void test_UpdateGroceryItem() throws Exception {
		GroceryItem groceryItem = new GroceryItem();
		groceryItem.setId((long) 2);
		groceryItem.setName("Rice");
		groceryItem.setQuantity((int) 45);
		groceryItem.setCategory("Cooking");
		Optional<GroceryItem> opt = Optional.of(groceryItem);

		when(groceryItemService.getItem(45)).thenReturn(opt);

		final ObjectMapper mapper = new ObjectMapper();
		final String jsonContent = mapper.writeValueAsString(groceryItem);
		this.mockMvc.perform(MockMvcRequestBuilders.put("/groceryItem/45").content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

	}
	
	@Test
	public void test_RetrieveCategoryItems() throws Exception {
		GroceryItem groceryItem = new GroceryItem();
		groceryItem.setId((long) 2);
		groceryItem.setName("Rice");
		groceryItem.setQuantity((int) 45);
		groceryItem.setCategory("Cooking");

		List<GroceryItem> groceryItems = Arrays.asList(groceryItem);
		when(groceryItemService.getAllCategoryItems("Cooking")).thenReturn(groceryItems);
		this.mockMvc.perform(get("/groceryItem/category/Cooking")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));;
	}

}