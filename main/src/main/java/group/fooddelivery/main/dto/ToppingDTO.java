package group.fooddelivery.main.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToppingDTO {
    private int id;

    @NotNull(message = "Topping name cannot be empty!")
    private String name;

    @NotNull(message = "Price cannot be empty!")
    private double price;
    // private List<FoodDTO> foodDTOs;
}
