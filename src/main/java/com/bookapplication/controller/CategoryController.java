package com.bookapplication.controller;

import com.bookapplication.entity.Category;
import com.bookapplication.model.requestDTO.CategoryDTO;
import com.bookapplication.model.requestresponse.ApiResponse;
import com.bookapplication.model.requestresponse.PaginationResponse;
import com.bookapplication.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * Endpoint to add a new category.
     *
     * @param categoryDTO The data of the category to be added.
     * @return ResponseEntity containing ApiResponse with the added category.
     */
    @PostMapping()
    public ResponseEntity<ApiResponse<Category>> addCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
    }

    /**
     * Endpoint to edit an existing category.
     *
     * @param categoryId  The ID of the category to be edited.
     * @param categoryDTO The updated data for the category.
     * @return ResponseEntity containing ApiResponse with the edited category.
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<Category>> editCategory(@PathVariable Long categoryId,
                                                              @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.editCategory(categoryId, categoryDTO));
    }

    /**
     * Endpoint to get all categories.
     *
     * @return ResponseEntity containing ApiResponse with a list of all categories.
     */
    @GetMapping()
    public ResponseEntity<PaginationResponse> getAllCategories(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                               @RequestParam(value = "limit", defaultValue = "5") int limit) {
        return ResponseEntity.ok(categoryService.getAllCategories(offset, limit));
    }

    /**
     * Endpoint to delete a category.
     *
     * @param categoryId The ID of the category to be deleted.
     * @return ResponseEntity containing ApiResponse with the deleted category.
     */
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<Category>> deleteCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }
}