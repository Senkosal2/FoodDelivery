package group.fooddelivery.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group.fooddelivery.main.dto.CategoryDTO;
import group.fooddelivery.main.dto.response.SuccessResponse;
import group.fooddelivery.main.service.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/categories")
@Validated // ensure validation
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<CategoryDTO>>> getCategorys() {
        List<CategoryDTO> categorys = categoryService.getCategories();
        SuccessResponse<List<CategoryDTO>> successResponse = new SuccessResponse<>();
        successResponse.setData(categorys);
        successResponse.setStatus(HttpStatus.OK);
        successResponse.setStatusCode(200);

        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
    
    @GetMapping("/{categoryId}")
    public ResponseEntity<SuccessResponse<CategoryDTO>> getCategoryById(
        @PathVariable int CategoryId) {
        CategoryDTO category = categoryService.getCategory(CategoryId);
        SuccessResponse<CategoryDTO> successResponse = new SuccessResponse<>();
        successResponse.setData(category);
        successResponse.setStatus(HttpStatus.OK);
        successResponse.setStatusCode(200);
        
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Void>> createCategory(
        @Valid @RequestBody CategoryDTO categoryDTO
    ) {
        categoryService.createCategory(categoryDTO);

        SuccessResponse<Void> successResponse = new SuccessResponse<>();
        successResponse.setMessage("Category created successfully!");
        successResponse.setStatus(HttpStatus.CREATED);
        successResponse.setStatusCode(201);
        
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<SuccessResponse<Void>> updateCategory(
        @PathVariable int categoryId,
        @Valid @RequestBody CategoryDTO categoryDTO
    ) {
        categoryService.updateCategory(categoryId, categoryDTO);

        SuccessResponse<Void> successResponse = new SuccessResponse<>();
        successResponse.setMessage("Category updated successfully!");
        successResponse.setStatus(HttpStatus.OK);
        successResponse.setStatusCode(200);

        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<SuccessResponse<Void>> deleteCategory(
        @PathVariable int categoryId
    ) {
        categoryService.deleteCategory(categoryId);

        SuccessResponse<Void> successResponse = new SuccessResponse<>();
        successResponse.setMessage("Category deleted successfully!");
        successResponse.setStatus(HttpStatus.NO_CONTENT);
        successResponse.setStatusCode(204);

        return new ResponseEntity<>(successResponse, HttpStatus.NO_CONTENT);
    }
}
