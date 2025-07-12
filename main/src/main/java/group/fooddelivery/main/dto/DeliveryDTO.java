package group.fooddelivery.main.dto;

import java.util.List;

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
    private List<DeliveryDetailsDTO> deliveryDetailsDTOs;
}
