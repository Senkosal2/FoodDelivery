package group.fooddelivery.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import group.fooddelivery.main.dto.OrderDetailDTO;
import group.fooddelivery.main.dto.response.SuccessResponse;
import group.fooddelivery.main.service.OrderDetailService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/order-details")
@Validated
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<OrderDetailDTO>>> getAllOrderDetails() {
        List<OrderDetailDTO> orderDetails = orderDetailService.getCategories(); // Consider renaming to getAllOrderDetails()

        SuccessResponse<List<OrderDetailDTO>> response = new SuccessResponse<>();
        response.setData(orderDetails);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{orderDetailId}")
    public ResponseEntity<SuccessResponse<OrderDetailDTO>> getOrderDetailById(@PathVariable int orderDetailId) {
        OrderDetailDTO orderDetail = orderDetailService.getOrderDetail(orderDetailId);

        SuccessResponse<OrderDetailDTO> response = new SuccessResponse<>();
        response.setData(orderDetail);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Void>> createOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO) {
        orderDetailService.createOrderDetail(orderDetailDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Order detail created successfully!");
        response.setStatus(HttpStatus.CREATED);
        response.setStatusCode(201);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{orderDetailId}")
    public ResponseEntity<SuccessResponse<Void>> updateOrderDetail(
        @PathVariable int orderDetailId,
        @Valid @RequestBody OrderDetailDTO orderDetailDTO) {

        orderDetailService.updateOrderDetail(orderDetailId, orderDetailDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Order detail updated successfully!");
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderDetailId}")
    public ResponseEntity<SuccessResponse<Void>> deleteOrderDetail(@PathVariable int orderDetailId) {
        orderDetailService.deleteOrderDetail(orderDetailId);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Order detail deleted successfully!");
        response.setStatus(HttpStatus.NO_CONTENT);
        response.setStatusCode(204);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
