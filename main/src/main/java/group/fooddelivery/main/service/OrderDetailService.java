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

import group.fooddelivery.main.dto.OrderDetailDTO;
import group.fooddelivery.main.entity.OrderDetail;
import group.fooddelivery.main.exception.NotFoundException;
import group.fooddelivery.main.mapper.CategoryMapper;
import group.fooddelivery.main.mapper.FoodMapper;
import group.fooddelivery.main.mapper.OrderDetailMapper;
import group.fooddelivery.main.mapper.OrderMapper;
import group.fooddelivery.main.mapper.ToppingMapper;
import group.fooddelivery.main.repository.OrderDetailRepository;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private OrderMapper orderMapper;
    
    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public OrderDetailDTO getOrderDetail(int orderDetailId) {
        OrderDetail existingOrderDetail = orderDetailRepository.findById(orderDetailId).orElseThrow(
            () -> new NotFoundException("OrderDetail does not exist!")
        );
        return orderDetailMapper.toOrderDetailDTO(existingOrderDetail);
    }

    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public List<OrderDetailDTO> getCategories() {
        List<OrderDetail> allExistingCategories = orderDetailRepository.findAll();
        if (allExistingCategories.isEmpty()) {
            throw new NotFoundException("No categories!");
        }
        return orderDetailMapper.toOrderDetailDTOs(allExistingCategories);
    }

    @Transactional // ensure db safely in case of error
    public void createOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = orderDetailMapper.toOrderDetail(orderDetailDTO);
        orderDetailRepository.save(orderDetail);
    }

    @Transactional
    public void updateOrderDetail(int orderDetailId, OrderDetailDTO orderDetailDTO) {
        OrderDetail existingOrderDetail = orderDetailRepository.findById(orderDetailId).orElseThrow(
            () -> new NotFoundException("Order Detail not found!")
        );

        existingOrderDetail.setFood(foodMapper.toFood(orderDetailDTO.getFoodDTO()));
        existingOrderDetail.setOrder(orderMapper.toOrder(orderDetailDTO.getOrderDTO()));
        existingOrderDetail.setQuantity(orderDetailDTO.getQuantity());

        orderDetailRepository.save(existingOrderDetail);
    }

    @Transactional
    public void deleteOrderDetail(int orderDetailId) {
        OrderDetail existingOrderDetail = orderDetailRepository.findById(orderDetailId).orElseThrow(
            () -> new NotFoundException("Order detail not found!")
        );
        orderDetailRepository.delete(existingOrderDetail);
    }
}
