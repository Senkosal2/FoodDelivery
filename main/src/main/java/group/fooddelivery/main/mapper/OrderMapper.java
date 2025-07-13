package group.fooddelivery.main.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import group.fooddelivery.main.dto.OrderDTO;
import group.fooddelivery.main.entity.Order;

@Component
public class OrderMapper {

    // @Autowired
    // @Lazy
    // private OrderDetailMapper orderDetailMapper;

    public Order toOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setOrderTime(orderDTO.getOrderTime());
        order.setStatus(orderDTO.getStatus());
        // order.setOrderDetails(orderDetailMapper.toOrderDetails(orderDTO.getOrderDetailsDTOs()));
        
        return order;
    }

    public List<Order> toOrders(List<OrderDTO> orderDTOs) {
        List<Order> orders = new ArrayList<>();
        for (OrderDTO orderDTO : orderDTOs) {
            orders.add(toOrder(orderDTO));
        }
        return orders;
    }

    public OrderDTO toOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderNumber(order.getOrderNumber());
        orderDTO.setOrderTime(order.getOrderTime());
        orderDTO.setStatus(order.getStatus());
        // orderDTO.setOrderDetailsDTOs(orderDetailMapper.toOrderDetailDTOs(order.getOrderDetails()));

        return orderDTO;
    }

    public List<OrderDTO> toOrderDTOs(List<Order> orders) {
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderDTOs.add(toOrderDTO(order));
        }
        return orderDTOs;
    }
}
