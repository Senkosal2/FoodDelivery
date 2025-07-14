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
public class DeliveryDetailDTO {
    private int id;

    @NotNull(message = "Required field!")
    private DeliveryDTO deliveryDTO;
    
    private boolean isAcceptOrder;
    private boolean isPreparingOrder;
    private boolean isDeliveryOnTheWay;
    private boolean isDelivered;
}
