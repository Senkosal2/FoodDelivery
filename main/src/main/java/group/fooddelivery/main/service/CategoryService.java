package group.fooddelivery.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group.fooddelivery.main.dto.CategoryDTO;
import group.fooddelivery.main.entity.Category;
import group.fooddelivery.main.exception.NotFoundException;
import group.fooddelivery.main.mapper.CategoryMapper;
import group.fooddelivery.main.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private CategoryMapper categoryMapper;
    
    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public CategoryDTO getCategory(int categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(
            () -> new NotFoundException("Category does not exist!")
        );
        return categoryMapper.toCategoryDTO(existingCategory);
    }

    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public List<CategoryDTO> getCategories() {
        List<Category> allExistingCategories = categoryRepository.findAll();
        if (allExistingCategories.isEmpty()) {
            throw new NotFoundException("No categories!");
        }
        return categoryMapper.toCategoryDTOs(allExistingCategories);
    }

    @Transactional // ensure db safely in case of error
    public void createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toCategory(categoryDTO);
        categoryRepository.save(category);
    }

    @Transactional
    public void updateCategory(int categoryId, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(
            () -> new NotFoundException("Category does not exist!")
        );
        existingCategory.setDescription(categoryDTO.getDescription());
        existingCategory.setName(categoryDTO.getName());

        categoryRepository.save(existingCategory);
    }

    @Transactional
    public void deleteCategory(int categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(
            () -> new NotFoundException("Category does not exist!")
        );
        categoryRepository.delete(existingCategory);
    }
}
