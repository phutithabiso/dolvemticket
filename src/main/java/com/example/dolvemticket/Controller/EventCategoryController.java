
package com.example.dolvemticket.Controller;

import com.example.dolvemticket.DTO.EventCategoryRequest;
import com.example.dolvemticket.DTO.EventCategoryResponse;
import com.example.dolvemticket.Services.EventCategoryServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class EventCategoryController {

    private final EventCategoryServices categoryServices;
    // CREATE CATEGORY
    @PostMapping
    public ResponseEntity<EventCategoryResponse> createCategory(
            @Valid @RequestBody EventCategoryRequest request) {

        EventCategoryResponse response = categoryServices.createCategory(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    // GET ALL CATEGORIES
    @GetMapping
    public ResponseEntity<List<EventCategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(
                categoryServices.getAllCategories()
        );
    }
    // GET CATEGORY BY ID
    @GetMapping("/{categoryId}")
    public ResponseEntity<EventCategoryResponse> getCategoryById(
            @PathVariable Long categoryId) {
        return ResponseEntity.ok(
                categoryServices.getCategoryById(categoryId)
        );
    }

    // UPDATE CATEGORY
    @PutMapping("/{categoryId}")
    public ResponseEntity<EventCategoryResponse> updateCategory(
            @PathVariable Long categoryId,
            @Valid @RequestBody EventCategoryRequest request) {
        return ResponseEntity.ok(
                categoryServices.updateCategory(categoryId, request)
        );
    }
    // DELETE CATEGORY
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryServices.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
