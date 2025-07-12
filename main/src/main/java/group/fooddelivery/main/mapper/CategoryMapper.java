package group.fooddelivery.main.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import group.fooddelivery.main.dto.CategoryDTO;
import group.fooddelivery.main.entity.Category;

@Component
public class CategoryMapper {

    @Autowired
    private FoodMapper foodMapper;

    public Category toCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setDescription(categoryDTO.getDescription());
        category.setName(categoryDTO.getName());
        category.setFoods(foodMapper.toFoods(categoryDTO.getFoodDTOs()));

        return category;
    }

    public List<Category> toCategories(List<CategoryDTO> categoryDTOs) {
        List<Category> categories = new ArrayList<>();
        for (CategoryDTO categoryDTO : categoryDTOs) {
            categories.add(toCategory(categoryDTO));
        }
        return categories;
    }

    public CategoryDTO toCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setName(category.getName());
        categoryDTO.setFoodDTOs(foodMapper.toFoodDTOs(category.getFoods()));

        return categoryDTO;
    }

    public List<CategoryDTO> toCategoryDTOs(List<Category> categories) {
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for (Category category : categories) {
            categoryDTOs.add(toCategoryDTO(category));
        }
        return categoryDTOs;
    }
}
