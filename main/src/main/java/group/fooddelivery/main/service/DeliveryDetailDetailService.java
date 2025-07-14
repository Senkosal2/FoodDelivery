package group.fooddelivery.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group.fooddelivery.main.dto.DeliveryDetailDTO;
import group.fooddelivery.main.entity.DeliveryDetail;
import group.fooddelivery.main.exception.NotFoundException;
import group.fooddelivery.main.mapper.DeliverDetailMapper;
import group.fooddelivery.main.mapper.DeliveryMapper;
import group.fooddelivery.main.mapper.OrderMapper;
import group.fooddelivery.main.repository.DeliveryDetailsRepository;

@Service
public class DeliveryDetailDetailService {
        @Autowired
    private DeliveryDetailsRepository deliveryDetailRepository;
    
    @Autowired
    private DeliverDetailMapper deliveryDetailMapper;

    @Autowired
    private DeliveryMapper deliveryMapper;
    
    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public DeliveryDetailDTO getDeliveryDetail(int deliveryId) {
        DeliveryDetail existingDeliveryDetail = deliveryDetailRepository.findById(deliveryId).orElseThrow(
            () -> new NotFoundException("DeliveryDetail does not exist!")
        );
        return deliveryDetailMapper.toDeliveryDetailDTO(existingDeliveryDetail);
    }

    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public List<DeliveryDetailDTO> getCategories() {
        List<DeliveryDetail> allExistingCategories = deliveryDetailRepository.findAll();
        if (allExistingCategories.isEmpty()) {
            throw new NotFoundException("No categories!");
        }
        return deliveryDetailMapper.toDeliveryDetailDTOs(allExistingCategories);
    }

    @Transactional // ensure db safely in case of error
    public void createDeliveryDetail(DeliveryDetailDTO deliveryDTO) {
        DeliveryDetail delivery = deliveryDetailMapper.toDeliveryDetail(deliveryDTO);
        deliveryDetailRepository.save(delivery);
    }

    @Transactional
    public void updateDeliveryDetail(int deliveryId, DeliveryDetailDTO deliveryDTO) {
        DeliveryDetail existingDeliveryDetail = deliveryDetailRepository.findById(deliveryId).orElseThrow(
            () -> new NotFoundException("DeliveryDetail does not exist!")
        );
        existingDeliveryDetail.setDelivery(deliveryMapper.toDelivery(deliveryDTO.getDeliveryDTO()));
        existingDeliveryDetail.setAcceptOrder(deliveryDTO.isAcceptOrder());
        existingDeliveryDetail.setDelivered(deliveryDTO.isDelivered());
        existingDeliveryDetail.setDeliveryOnTheWay(deliveryDTO.isDeliveryOnTheWay());
        existingDeliveryDetail.setPreparingOrder(deliveryDTO.isPreparingOrder());

        deliveryDetailRepository.save(existingDeliveryDetail);
    }

    @Transactional
    public void deleteDeliveryDetail(int deliveryId) {
        DeliveryDetail existingDeliveryDetail = deliveryDetailRepository.findById(deliveryId).orElseThrow(
            () -> new NotFoundException("DeliveryDetail does not exist!")
        );
        deliveryDetailRepository.delete(existingDeliveryDetail);
    }
}
