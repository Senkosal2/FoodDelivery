package group.fooddelivery.main.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import group.fooddelivery.main.dto.OrderDTO;
import group.fooddelivery.main.entity.Order;

@Component
public class OrderMapper {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public Order toOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setOrderTime(orderDTO.getOrderTime());
        order.setStatus(orderDTO.getStatus());
        order.setOrderDetails(orderDetailMapper.toOrderDetails(orderDTO.getOrderDetailsDTOs()));
        
        return order;
    }

    public OrderDTO toOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderNumber(order.getOrderNumber());
        orderDTO.setOrderTime(order.getOrderTime());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setOrderDetailsDTOs(orderDetailMapper.toOrderDetailDTOs(order.getOrderDetails()));

        return orderDTO;
    }
}
