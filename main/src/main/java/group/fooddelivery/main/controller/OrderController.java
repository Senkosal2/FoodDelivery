package group.fooddelivery.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import group.fooddelivery.main.dto.OrderDTO;
import group.fooddelivery.main.dto.response.SuccessResponse;
import group.fooddelivery.main.service.OrderService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/orders")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<OrderDTO>>> getAllOrders() {
        List<OrderDTO> orders = orderService.getCategories(); // consider renaming this method

        SuccessResponse<List<OrderDTO>> response = new SuccessResponse<>();
        response.setData(orders);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<SuccessResponse<OrderDTO>> getOrderById(@PathVariable int orderId) {
        OrderDTO order = orderService.getOrder(orderId);

        SuccessResponse<OrderDTO> response = new SuccessResponse<>();
        response.setData(order);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Void>> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Order created successfully!");
        response.setStatus(HttpStatus.CREATED);
        response.setStatusCode(201);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<SuccessResponse<Void>> updateOrder(
        @PathVariable int orderId,
        @Valid @RequestBody OrderDTO orderDTO) {

        orderService.updateOrder(orderId, orderDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Order updated successfully!");
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<SuccessResponse<Void>> deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Order deleted successfully!");
        response.setStatus(HttpStatus.NO_CONTENT);
        response.setStatusCode(204);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
