package com.example.library.service;

import com.example.library.dto.CategoryDTO;
import com.example.library.mapper.EntityToDTOMapper;
import com.example.library.model.Category;
import com.example.library.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(EntityToDTOMapper::toCategoryDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(EntityToDTOMapper::toCategoryDTO);
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = EntityToDTOMapper.toCategoryEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return EntityToDTOMapper.toCategoryDTO(savedCategory);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDetails) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(categoryDetails.getName());
                    Category updatedCategory = categoryRepository.save(category);
                    return EntityToDTOMapper.toCategoryDTO(updatedCategory);
                }).orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
