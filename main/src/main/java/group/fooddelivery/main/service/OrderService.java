package group.fooddelivery.main.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group.fooddelivery.main.dto.OrderDTO;
import group.fooddelivery.main.entity.Order;
import group.fooddelivery.main.exception.NotFoundException;
import group.fooddelivery.main.mapper.CategoryMapper;
import group.fooddelivery.main.mapper.OrderMapper;
import group.fooddelivery.main.mapper.ToppingMapper;
import group.fooddelivery.main.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ToppingMapper toppingMapper;
    
    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public OrderDTO getOrder(int orderId) {
        Order existingOrder = orderRepository.findById(orderId).orElseThrow(
            () -> new NotFoundException("Order does not exist!")
        );
        return orderMapper.toOrderDTO(existingOrder);
    }

    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public List<OrderDTO> getCategories() {
        List<Order> allExistingCategories = orderRepository.findAll();
        if (allExistingCategories.isEmpty()) {
            throw new NotFoundException("No categories!");
        }
        return orderMapper.toOrderDTOs(allExistingCategories);
    }

    @Transactional // ensure db safely in case of error
    public void createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toOrder(orderDTO);
        orderRepository.save(order);
    }

    @Transactional
    public void updateOrder(int orderId, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(orderId).orElseThrow(
            () -> new NotFoundException("Order does not exist!")
        );

        existingOrder.setOrderDate(orderDTO.getOrderDate() == null ? Date.valueOf(LocalDate.now()) : orderDTO.getOrderDate());
        existingOrder.setOrderTime(orderDTO.getOrderTime() == null ? Time.valueOf(LocalTime.now()) : orderDTO.getOrderTime());
        existingOrder.setOrderNumber(orderDTO.getOrderNumber());
        existingOrder.setStatus(orderDTO.getStatus());

        orderRepository.save(existingOrder);
    }

    @Transactional
    public void deleteOrder(int orderId) {
        Order existingOrder = orderRepository.findById(orderId).orElseThrow(
            () -> new NotFoundException("Order does not exist!")
        );
        orderRepository.delete(existingOrder);
    }
}
