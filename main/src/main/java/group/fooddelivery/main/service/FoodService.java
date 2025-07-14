package group.fooddelivery.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group.fooddelivery.main.dto.FoodDTO;
import group.fooddelivery.main.entity.Food;
import group.fooddelivery.main.exception.NotFoundException;
import group.fooddelivery.main.mapper.CategoryMapper;
import group.fooddelivery.main.mapper.FoodMapper;
import group.fooddelivery.main.mapper.ToppingMapper;
import group.fooddelivery.main.repository.FoodRepository;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;
    
    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ToppingMapper toppingMapper;
    
    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public FoodDTO getFood(int foodId) {
        Food existingFood = foodRepository.findById(foodId).orElseThrow(
            () -> new NotFoundException("Food does not exist!")
        );
        return foodMapper.toFoodDTO(existingFood);
    }

    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public List<FoodDTO> getCategories() {
        List<Food> allExistingCategories = foodRepository.findAll();
        if (allExistingCategories.isEmpty()) {
            throw new NotFoundException("No categories!");
        }
        return foodMapper.toFoodDTOs(allExistingCategories);
    }

    @Transactional // ensure db safely in case of error
    public void createFood(FoodDTO foodDTO) {
        Food food = foodMapper.toFood(foodDTO);
        foodRepository.save(food);
    }

    @Transactional
    public void updateFood(int foodId, FoodDTO foodDTO) {
        Food existingFood = foodRepository.findById(foodId).orElseThrow(
            () -> new NotFoundException("Food does not exist!")
        );
        existingFood.setName(foodDTO.getName());
        existingFood.setDescription(foodDTO.getDescription());
        existingFood.setPrice(foodDTO.getPrice());
        existingFood.setImageUrl(foodDTO.getImageUrl());
        existingFood.setLikes(foodDTO.getLike());
        existingFood.setCategories(categoryMapper.toCategories(foodDTO.getCategoriesDTO()));
        existingFood.setToppings(toppingMapper.toToppings(foodDTO.getToppingDTOs()));

        foodRepository.save(existingFood);
    }

    @Transactional
    public void deleteFood(int foodId) {
        Food existingFood = foodRepository.findById(foodId).orElseThrow(
            () -> new NotFoundException("Food does not exist!")
        );
        foodRepository.delete(existingFood);
    }
}
