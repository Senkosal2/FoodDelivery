package group.fooddelivery.main.dto;

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
    private OrderDTO orderDTO;
    // private List<DeliveryDetailDTO> deliveryDetailsDTOs;
}
