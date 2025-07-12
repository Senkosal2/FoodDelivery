package group.fooddelivery.main.dto;
import java.util.List;

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
    private String name;
    private double price;
    private String description;
    private long like;
    private List<CategoryDTO> categoriesDTO;
    private String imageUrl;
    private List<ToppingDTO> toppingDTOs;
}
