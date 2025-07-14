package group.fooddelivery.main.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {
    private int id;
    
    @NotNull(message = "Required field!")
    private OrderDTO orderDTO;
    // private List<DeliveryDetailDTO> deliveryDetailsDTOs;
}
