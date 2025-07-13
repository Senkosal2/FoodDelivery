package group.fooddelivery.main.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private int id;

    @NotNull(message = "Category name cannot be empty!")
    private String name;

    private String description;
    // private List<FoodDTO> foodDTOs;
}
