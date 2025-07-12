package group.fooddelivery.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private int id;
    private OrderDTO orderDTO;
    private FoodDTO foodDTO;
    private int quantity;

    
    
}
