package group.fooddelivery.main.mapper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import group.fooddelivery.main.dto.FoodDTO;
import group.fooddelivery.main.entity.Food;

@Component
public class FoodMapper {

    @Autowired
    @Lazy
    private CategoryMapper categoryMapper;

    @Autowired
    @Lazy
    private ToppingMapper toppingMapper;
    
    public Food toFood(FoodDTO foodDTO) {
        Food food = new Food();
        food.setId(foodDTO.getId());
        food.setDescription(foodDTO.getDescription());
        food.setImageUrl(foodDTO.getImageUrl());
        food.setName(foodDTO.getName());
        food.setLikes(foodDTO.getLike());
        food.setPrice(foodDTO.getPrice());
        food.setCategories(categoryMapper.toCategories(foodDTO.getCategoriesDTO()));
        food.setToppings(toppingMapper.toToppings(foodDTO.getToppingDTOs()));

        return food;
    }

    public List<Food> toFoods(List<FoodDTO> foodDTOs) {
        List<Food> foods = new ArrayList<>();
        for (FoodDTO foodDTO : foodDTOs) {
            foods.add(toFood(foodDTO));
        }
        return foods;
    }

    public FoodDTO toFoodDTO(Food food) {
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setId(food.getId());
        foodDTO.setDescription(food.getDescription());
        foodDTO.setImageUrl(food.getImageUrl());
        foodDTO.setName(food.getName());
        foodDTO.setLike(food.getLikes());
        foodDTO.setPrice(food.getPrice());
        foodDTO.setCategoriesDTO(categoryMapper.toCategoryDTOs(food.getCategories()));
        foodDTO.setToppingDTOs(toppingMapper.toToppingDTOs(food.getToppings()));
        
        return foodDTO;
    }

    public List<FoodDTO> toFoodDTOs(List<Food> foods) {
        List<FoodDTO> foodDTOs = new ArrayList<>();
        for (Food food : foods) {
            foodDTOs.add(toFoodDTO(food));
        }
        return foodDTOs;
    }
}
