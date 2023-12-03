package com.bookapplication.service;

import com.bookapplication.entity.Category;
import com.bookapplication.model.requestDTO.CategoryDTO;
import com.bookapplication.model.requestresponse.ApiResponse;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public interface CategoryService {
     ApiResponse<Category> addCategory(CategoryDTO categoryDTO);
     ApiResponse<Category> editCategory(Long categoryId,CategoryDTO categoryDTO);
     ApiResponse<List<Category>> getAllCategories();
     ApiResponse<Category> deleteCategory(Long categoryId);
}
