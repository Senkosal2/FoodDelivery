package group.fooddelivery.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import group.fooddelivery.main.dto.DeliveryDTO;
import group.fooddelivery.main.dto.response.SuccessResponse;
import group.fooddelivery.main.service.DeliveryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/deliveries")
@Validated
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<DeliveryDTO>>> getAllDeliveries() {
        List<DeliveryDTO> deliveries = deliveryService.getCategories(); // using getCategories() from your service

        SuccessResponse<List<DeliveryDTO>> response = new SuccessResponse<>();
        response.setData(deliveries);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<SuccessResponse<DeliveryDTO>> getDeliveryById(@PathVariable int deliveryId) {
        DeliveryDTO delivery = deliveryService.getDelivery(deliveryId);

        SuccessResponse<DeliveryDTO> response = new SuccessResponse<>();
        response.setData(delivery);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Void>> createDelivery(@Valid @RequestBody DeliveryDTO deliveryDTO) {
        deliveryService.createDelivery(deliveryDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Delivery created successfully!");
        response.setStatus(HttpStatus.CREATED);
        response.setStatusCode(201);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{deliveryId}")
    public ResponseEntity<SuccessResponse<Void>> updateDelivery(
        @PathVariable int deliveryId,
        @Valid @RequestBody DeliveryDTO deliveryDTO
    ) {
        deliveryService.updateDelivery(deliveryId, deliveryDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Delivery updated successfully!");
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{deliveryId}")
    public ResponseEntity<SuccessResponse<Void>> deleteDelivery(@PathVariable int deliveryId) {
        deliveryService.deleteDelivery(deliveryId);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Delivery deleted successfully!");
        response.setStatus(HttpStatus.NO_CONTENT);
        response.setStatusCode(204);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
