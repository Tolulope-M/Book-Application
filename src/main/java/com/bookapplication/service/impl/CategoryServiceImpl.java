package com.bookapplication.service.impl;

import com.bookapplication.entity.Category;
import com.bookapplication.exception.ClientSideException;
import com.bookapplication.exception.ServerSideException;
import com.bookapplication.model.requestDTO.CategoryDTO;
import com.bookapplication.model.requestresponse.ApiResponse;
import com.bookapplication.repository.CategoryRepository;
import com.bookapplication.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.bookapplication.constants.AppConstants.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public ApiResponse<Category> addCategory(CategoryDTO categoryDTO) {
            Optional<Category> categoryResponse =
                    categoryRepository.findByCategoryName(categoryDTO.getName());

            if (categoryResponse.isPresent()) {
                throw new ClientSideException(CATEGORY_ALREADY_EXIST);
            }
            Category category = new Category();
            category.setCategoryName(categoryDTO.getName());
            Category savedCategory = categoryRepository.save(category);

            log.info("saved category >> {}", savedCategory);
            return ApiResponse.<Category>builder()
                    .status(SUCCESS_STATUS_CODE)
                    .message(SUCCESS)
                    .data(savedCategory)
                    .build();
    }

    @Override
    public ApiResponse<Category> editCategory(Long categoryId, CategoryDTO categoryDTO) {
            Optional<Category> categoryResponse =
                    categoryRepository.findById(categoryId);
            if (!categoryResponse.isPresent()) {
                throw new ClientSideException(CATEGORY_NOT_FOUND);
            }
            Category category = categoryResponse.get();
            category.setCategoryName(categoryDTO.getName());
            Category savedCategory = categoryRepository.save(category);

            log.info("saved category >> {}", savedCategory);
            return ApiResponse.<Category>builder()
                    .status(SUCCESS_STATUS_CODE)
                    .message(SUCCESS)
                    .data(savedCategory)
                    .build();
    }

    @Override
    public ApiResponse<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        log.info("category list >> {}", categoryList);
        return ApiResponse.<List<Category>>builder()
                .status(SUCCESS_STATUS_CODE)
                .message(SUCCESS)
                .data(categoryList)
                .build();
    }

    @Override
    public ApiResponse<Category> deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isEmpty()) {
            throw new ClientSideException(CATEGORY_NOT_FOUND);
        }

        Category categoryToDelete = categoryOptional.get();
        categoryRepository.delete(categoryToDelete);

        log.info("Deleted category >> {}", categoryToDelete);

        return ApiResponse.<Category>builder()
                .status(SUCCESS_STATUS_CODE)
                .message(SUCCESS)
                .data(categoryToDelete)
                .build();
    }
}
