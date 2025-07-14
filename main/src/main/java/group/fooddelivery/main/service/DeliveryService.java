package group.fooddelivery.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group.fooddelivery.main.dto.DeliveryDTO;
import group.fooddelivery.main.entity.Delivery;
import group.fooddelivery.main.exception.NotFoundException;
import group.fooddelivery.main.mapper.DeliveryMapper;
import group.fooddelivery.main.mapper.OrderMapper;
import group.fooddelivery.main.repository.DeliveryRepository;

@Service
public class DeliveryService {
        @Autowired
    private DeliveryRepository deliveryRepository;
    
    @Autowired
    private DeliveryMapper deliveryMapper;

    @Autowired
    private OrderMapper orderMapper;
    
    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public DeliveryDTO getDelivery(int deliveryId) {
        Delivery existingDelivery = deliveryRepository.findById(deliveryId).orElseThrow(
            () -> new NotFoundException("Delivery does not exist!")
        );
        return deliveryMapper.toDeliveryDTO(existingDelivery);
    }

    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public List<DeliveryDTO> getCategories() {
        List<Delivery> allExistingCategories = deliveryRepository.findAll();
        if (allExistingCategories.isEmpty()) {
            throw new NotFoundException("No categories!");
        }
        return deliveryMapper.toDeliveryDTOs(allExistingCategories);
    }

    @Transactional // ensure db safely in case of error
    public void createDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = deliveryMapper.toDelivery(deliveryDTO);
        deliveryRepository.save(delivery);
    }

    @Transactional
    public void updateDelivery(int deliveryId, DeliveryDTO deliveryDTO) {
        Delivery existingDelivery = deliveryRepository.findById(deliveryId).orElseThrow(
            () -> new NotFoundException("Delivery does not exist!")
        );
        existingDelivery.setOrder(orderMapper.toOrder(deliveryDTO.getOrderDTO()));

        deliveryRepository.save(existingDelivery);
    }

    @Transactional
    public void deleteDelivery(int deliveryId) {
        Delivery existingDelivery = deliveryRepository.findById(deliveryId).orElseThrow(
            () -> new NotFoundException("Delivery does not exist!")
        );
        deliveryRepository.delete(existingDelivery);
    }
}
