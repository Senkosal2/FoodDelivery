package group.fooddelivery.main.dto;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {
    private int id;
    
    @NotNull(message = "Food name cannot be empty!")
    private String name;

    @NotNull(message = "Food price cannot be empty!")
    private double price;

    private String description;
    private long like;

    @NotEmpty(message = "Food must have at least 1 category")
    private List<CategoryDTO> categoriesDTO;
    private String imageUrl;

    private List<ToppingDTO> toppingDTOs;
}
