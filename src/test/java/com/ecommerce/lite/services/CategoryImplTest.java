package com.ecommerce.lite.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ecommerce.lite.entities.Categories;
import com.ecommerce.lite.repositories.ICategoryRepository;
import com.ecommerce.lite.service.impl.CategoryServiceImpl;

public class CategoryImplTest {
	
	 	@InjectMocks
	    private CategoryServiceImpl categoryServiceImpl;

	    @Mock
	    private ICategoryRepository iCategoryRepository;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void createCategory_success() throws Exception {
	        Categories category = new Categories();
	        when(iCategoryRepository.save(category)).thenReturn(category);
	        Categories result = categoryServiceImpl.createCategory(category);
	        assertNotNull(result);
	        verify(iCategoryRepository, times(1)).save(category);
	    }

	    @Test
	    void createCategory_exception() {
	        Categories category = new Categories();
	        when(iCategoryRepository.save(category)).thenThrow(new RuntimeException("Database error"));
	        Exception exception = assertThrows(Exception.class, () -> categoryServiceImpl.createCategory(category));
	        assertTrue(exception.getMessage().contains("Error creating category"));
	        verify(iCategoryRepository, times(1)).save(category);
	    }

	    @Test
	    void deleteCategoryById_success() throws Exception {
	        Integer categoryId = 1;
	        Categories category = new Categories();
	        when(iCategoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
	        categoryServiceImpl.deleteCategoryById(categoryId);
	        verify(iCategoryRepository, times(1)).findById(categoryId);
	        verify(iCategoryRepository, times(1)).deleteById(categoryId);
	    }

	    @Test
	    void deleteCategoryById_categoryNotFound() throws Exception {
	        // Arrange
	        Integer categoryId = 1;
	        when(iCategoryRepository.findById(categoryId)).thenReturn(Optional.empty());

	        // Act
	        categoryServiceImpl.deleteCategoryById(categoryId);

	        // Assert
	        verify(iCategoryRepository, times(1)).findById(categoryId);
	        verify(iCategoryRepository, never()).deleteById(anyInt());
	    }

	    @Test
	    void deleteCategoryById_exception() {
	        // Arrange
	        Integer categoryId = 1;
	        when(iCategoryRepository.findById(categoryId)).thenReturn(Optional.of(new Categories()));
	        doThrow(new RuntimeException("Database error")).when(iCategoryRepository).deleteById(categoryId);

	        // Act & Assert
	        Exception exception = assertThrows(Exception.class, () -> categoryServiceImpl.deleteCategoryById(categoryId));
	        assertTrue(exception.getMessage().contains("Error deleting category"));
	        verify(iCategoryRepository, times(1)).deleteById(categoryId);
	    }

	    @Test
	    void getAllCategories_success() {
	        List<Categories> categoriesList = new ArrayList<>();
	        categoriesList.add(new Categories());
	        when(iCategoryRepository.findAll()).thenReturn(categoriesList);

	        List<Categories> result = categoryServiceImpl.getAllCategories();

	        assertNotNull(result);
	        assertEquals(1, result.size());
	        verify(iCategoryRepository, times(1)).findAll();
	    }
}
