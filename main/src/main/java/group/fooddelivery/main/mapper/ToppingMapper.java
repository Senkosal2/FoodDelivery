package group.fooddelivery.main.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import group.fooddelivery.main.dto.ToppingDTO;
import group.fooddelivery.main.entity.Topping;

@Component
public class ToppingMapper {

    @Autowired
    private FoodMapper foodMapper;

    public Topping toTopping(ToppingDTO toppingDTO) {
        Topping topping = new Topping();
        topping.setId(toppingDTO.getId());
        topping.setName(toppingDTO.getName());
        topping.setPrice(toppingDTO.getPrice());
        topping.setFoods(foodMapper.toFoods(toppingDTO.getFoodDTOs()));

        return topping;
    }

    public List<Topping> toToppings(List<ToppingDTO> toppingDTOs) {
        List<Topping> toppings = new ArrayList<>();
        for (ToppingDTO toppingDTO : toppingDTOs) {
            toppings.add(toTopping(toppingDTO));
        }
        return toppings;
    }

    public ToppingDTO toToppingDTO(Topping topping) {
        ToppingDTO toppingDTO = new ToppingDTO();
        toppingDTO.setId(topping.getId());
        toppingDTO.setName(topping.getName());
        toppingDTO.setPrice(topping.getPrice());
        toppingDTO.setFoodDTOs(foodMapper.toFoodDTOs(topping.getFoods()));
        return toppingDTO;
    }

    public List<ToppingDTO> toToppingDTOs(List<Topping> toppings) {
        List<ToppingDTO> toppingDTOs = new ArrayList<>();
        for (Topping topping : toppings) {
            toppingDTOs.add(toToppingDTO(topping));
        }
        return toppingDTOs;
    }
}
