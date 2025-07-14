package group.fooddelivery.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import group.fooddelivery.main.dto.DeliveryDetailDTO;
import group.fooddelivery.main.dto.response.SuccessResponse;
import group.fooddelivery.main.service.DeliveryDetailDetailService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/delivery-details")
@Validated
public class DeliveryDetailController {

    @Autowired
    private DeliveryDetailDetailService deliveryDetailService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<DeliveryDetailDTO>>> getAllDeliveryDetails() {
        List<DeliveryDetailDTO> deliveryDetails = deliveryDetailService.getCategories(); // consider renaming to getAllDeliveryDetails()

        SuccessResponse<List<DeliveryDetailDTO>> response = new SuccessResponse<>();
        response.setData(deliveryDetails);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{deliveryDetailId}")
    public ResponseEntity<SuccessResponse<DeliveryDetailDTO>> getDeliveryDetailById(
        @PathVariable int deliveryDetailId) {
        
        DeliveryDetailDTO detail = deliveryDetailService.getDeliveryDetail(deliveryDetailId);

        SuccessResponse<DeliveryDetailDTO> response = new SuccessResponse<>();
        response.setData(detail);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Void>> createDeliveryDetail(
        @Valid @RequestBody DeliveryDetailDTO deliveryDetailDTO) {
        
        deliveryDetailService.createDeliveryDetail(deliveryDetailDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Delivery detail created successfully!");
        response.setStatus(HttpStatus.CREATED);
        response.setStatusCode(201);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{deliveryDetailId}")
    public ResponseEntity<SuccessResponse<Void>> updateDeliveryDetail(
        @PathVariable int deliveryDetailId,
        @Valid @RequestBody DeliveryDetailDTO deliveryDetailDTO) {
        
        deliveryDetailService.updateDeliveryDetail(deliveryDetailId, deliveryDetailDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Delivery detail updated successfully!");
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{deliveryDetailId}")
    public ResponseEntity<SuccessResponse<Void>> deleteDeliveryDetail(
        @PathVariable int deliveryDetailId) {
        
        deliveryDetailService.deleteDeliveryDetail(deliveryDetailId);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Delivery detail deleted successfully!");
        response.setStatus(HttpStatus.NO_CONTENT);
        response.setStatusCode(204);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
