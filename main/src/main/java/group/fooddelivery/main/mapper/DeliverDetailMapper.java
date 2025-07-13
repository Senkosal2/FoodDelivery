package group.fooddelivery.main.mapper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import group.fooddelivery.main.dto.DeliveryDetailDTO;
import group.fooddelivery.main.entity.DeliveryDetail;

@Component
public class DeliverDetailMapper {

    @Autowired
    @Lazy
    private DeliveryMapper deliveryMapper;

    public DeliveryDetail toDeliveryDetail(DeliveryDetailDTO deliveryDetailDTO) {
        DeliveryDetail deliveryDetail = new DeliveryDetail();
        deliveryDetail.setId(deliveryDetailDTO.getId());
        deliveryDetail.setAcceptOrder(deliveryDetailDTO.isAcceptOrder());
        deliveryDetail.setDelivered(deliveryDetailDTO.isDelivered());
        deliveryDetail.setDeliveryOnTheWay(deliveryDetailDTO.isDeliveryOnTheWay());
        deliveryDetail.setPreparingOrder(deliveryDetailDTO.isPreparingOrder());
        deliveryDetail.setDelivery(deliveryMapper.toDelivery(deliveryDetailDTO.getDeliveryDTO()));

        return deliveryDetail;
    }

    public List<DeliveryDetail> toDeliveryDetails(List<DeliveryDetailDTO> deliveryDetailDTOs) {
        List<DeliveryDetail> deliveryDetails = new ArrayList<>();
        for (DeliveryDetailDTO deliveryDetailDTO : deliveryDetailDTOs) {
            deliveryDetails.add(toDeliveryDetail(deliveryDetailDTO));
        }
        return deliveryDetails;
    }

    public DeliveryDetailDTO toDeliveryDetailDTO(DeliveryDetail deliveryDetail) {
        DeliveryDetailDTO deliveryDetailDTO = new DeliveryDetailDTO();
        deliveryDetailDTO.setId(deliveryDetail.getId());
        deliveryDetailDTO.setAcceptOrder(deliveryDetail.isAcceptOrder());
        deliveryDetailDTO.setDelivered(deliveryDetail.isDelivered());
        deliveryDetailDTO.setDeliveryOnTheWay(deliveryDetail.isDeliveryOnTheWay());
        deliveryDetailDTO.setPreparingOrder(deliveryDetail.isPreparingOrder());
        deliveryDetailDTO.setDeliveryDTO(deliveryMapper.toDeliveryDTO(deliveryDetail.getDelivery()));

        return deliveryDetailDTO;
    }

    public List<DeliveryDetailDTO> toDeliveryDetailDTOs(List<DeliveryDetail> deliveryDetails) {
        List<DeliveryDetailDTO> deliveryDetailDTOs = new ArrayList<>();
        for (DeliveryDetail deliveryDetail : deliveryDetails) {
            deliveryDetailDTOs.add(toDeliveryDetailDTO(deliveryDetail));
        }
        return deliveryDetailDTOs;
    }
}
