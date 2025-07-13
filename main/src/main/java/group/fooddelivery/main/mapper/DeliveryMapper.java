package group.fooddelivery.main.mapper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import group.fooddelivery.main.dto.DeliveryDTO;
import group.fooddelivery.main.entity.Delivery;

@Component
public class DeliveryMapper {

    // @Autowired
    // @Lazy
    // private DeliverDetailMapper deliverDetailMapper;

    @Autowired
    @Lazy
    private OrderMapper orderMapper;

    public Delivery toDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setId(deliveryDTO.getId());
        // delivery.setDeliveryDetails(deliverDetailMapper.toDeliveryDetails(deliveryDTO.getDeliveryDetailsDTOs()));
        delivery.setOrder(orderMapper.toOrder(deliveryDTO.getOrderDTO()));

        return delivery;
    }

    public List<Delivery> toDeliveries(List<DeliveryDTO> DeliveryDTOs) {
        List<Delivery> Deliveries = new ArrayList<>();
        for (DeliveryDTO DeliveryDTO : DeliveryDTOs) {
            Deliveries.add(toDelivery(DeliveryDTO));
        }
        return Deliveries;
    }

    public DeliveryDTO toDeliveryDTO(Delivery delivery) {
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setId(delivery.getId());
        deliveryDTO.setOrderDTO(orderMapper.toOrderDTO(delivery.getOrder()));

        return deliveryDTO;
    }

    public List<DeliveryDTO> toDeliveryDTOs(List<Delivery> Deliveries) {
        List<DeliveryDTO> DeliveryDTOs = new ArrayList<>();
        for (Delivery Delivery : Deliveries) {
            DeliveryDTOs.add(toDeliveryDTO(Delivery));
        }
        return DeliveryDTOs;
    }
}
