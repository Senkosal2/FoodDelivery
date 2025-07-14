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
public class OrderDetailDTO {
    private int id;

    @NotNull(message = "Required field!")
    private OrderDTO orderDTO;

    @NotNull(message = "Required field!")
    private FoodDTO foodDTO;
    private int quantity;

    
    
}
