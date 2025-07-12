package group.fooddelivery.main.dto;

import java.util.List;
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
    private String name;
    private double price;
    private List<FoodDTO> foodDTOs;
}
