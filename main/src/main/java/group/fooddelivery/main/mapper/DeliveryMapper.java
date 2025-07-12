package group.fooddelivery.main.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import group.fooddelivery.main.dto.DeliveryDTO;
import group.fooddelivery.main.entity.Delivery;

@Component
public class DeliveryMapper {

    @Autowired
    private DeliverDetailMapper deliverDetailMapper;

    @Autowired
    private OrderMapper orderMapper;

    public Delivery toDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setId(deliveryDTO.getId());
        delivery.setDeliveryDetails(deliverDetailMapper.toDeliveryDetails(deliveryDTO.getDeliveryDetailsDTOs()));
        delivery.setOrder(orderMapper.toOrder(deliveryDTO.getOrderDTO()));

        return delivery;
    }

    public DeliveryDTO toDeliveryDTO(Delivery delivery) {
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setId(delivery.getId());
        deliveryDTO.setOrderDTO(orderMapper.toOrderDTO(delivery.getOrder()));

        return deliveryDTO;
    }
}
