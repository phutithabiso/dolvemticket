
package com.example.dolvemticket.Services;

import com.example.dolvemticket.DTO.EventCategoryRequest;
import com.example.dolvemticket.DTO.EventCategoryResponse;
import com.example.dolvemticket.Entity.EventCategory;
import com.example.dolvemticket.Repository.EventCategoryRepository;
import com.example.dolvemticket.Exception.BadRequestException;
import com.example.dolvemticket.Exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventCategoryServices {

    private final EventCategoryRepository categoryRepository;

    // CREATE CATEGORY
    public EventCategoryResponse createCategory(EventCategoryRequest request) {

        if (categoryRepository.existsByCategoryName(request.categoryName())) {
            throw new BadRequestException("Category already exists");
        }

        EventCategory category = new EventCategory();

        category.setCategoryName(request.categoryName());
        category.setDescription(request.description());

        EventCategory savedCategory = categoryRepository.save(category);

        return mapToResponse(savedCategory);
    }

    // GET ALL CATEGORIES
    @Transactional(readOnly = true)
    public List<EventCategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // GET CATEGORY BY ID
    @Transactional(readOnly = true)
    public EventCategoryResponse getCategoryById(Long categoryId) {

        EventCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        return mapToResponse(category);
    }

    // UPDATE CATEGORY
    public EventCategoryResponse updateCategory(
            Long categoryId,
            EventCategoryRequest request) {

        EventCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        if (!category.getCategoryName().equalsIgnoreCase(request.categoryName())
                && categoryRepository.existsByCategoryName(request.categoryName())) {
            throw new BadRequestException("Category name already exists");
        }
        category.setCategoryName(request.categoryName());
        category.setDescription(request.description());
        EventCategory updatedCategory = categoryRepository.save(category);
        return mapToResponse(updatedCategory);
    }

    // DELETE CATEGORY
    public void deleteCategory(Long categoryId) {

        EventCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        if (!category.getEvents().isEmpty()) {
            throw new BadRequestException("Cannot delete category because events are associated with it");
        }
        categoryRepository.delete(category);
    }

    // ENTITY TO RESPONSE
    private EventCategoryResponse mapToResponse(EventCategory category) {
        return new EventCategoryResponse(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }
}
