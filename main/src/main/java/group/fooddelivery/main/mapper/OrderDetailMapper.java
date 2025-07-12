package group.fooddelivery.main.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import group.fooddelivery.main.dto.OrderDetailDTO;
import group.fooddelivery.main.entity.OrderDetail;

@Component
public class OrderDetailMapper {

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private OrderMapper orderMapper;
    
    public OrderDetail toOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailDTO.getId());
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setFood(foodMapper.toFood(orderDetailDTO.getFoodDTO()));
        orderDetail.setOrder(orderMapper.toOrder(orderDetailDTO.getOrderDTO()));

        return orderDetail;
    }

    public List<OrderDetail> toOrderDetails(List<OrderDetailDTO> orderDetailDTOs) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOs) {
            orderDetails.add(toOrderDetail(orderDetailDTO));
        }
        return orderDetails;
    }

    public OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setId(orderDetail.getId());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        orderDetailDTO.setFoodDTO(foodMapper.toFoodDTO(orderDetail.getFood()));
        orderDetailDTO.setOrderDTO(orderMapper.toOrderDTO(orderDetail.getOrder()));
        
        return orderDetailDTO;
    }

    public List<OrderDetailDTO> toOrderDetailDTOs(List<OrderDetail> orderDetails) {
        List<OrderDetailDTO> orderDetailDTOs = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetailDTOs.add(toOrderDetailDTO(orderDetail));
        }
        return orderDetailDTOs;
    }
}
