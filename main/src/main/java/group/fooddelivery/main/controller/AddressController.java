package group.fooddelivery.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import group.fooddelivery.main.dto.AddressDTO;
import group.fooddelivery.main.dto.response.SuccessResponse;
import group.fooddelivery.main.service.AddressService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/addresses")
@Validated
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<AddressDTO>>> getAllAddresses() {
        List<AddressDTO> addresses = addressService.getAllAddresses();

        SuccessResponse<List<AddressDTO>> response = new SuccessResponse<>();
        response.setData(addresses);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<AddressDTO>> getAddressById(@PathVariable int id) {
        AddressDTO address = addressService.getAddress(id);

        SuccessResponse<AddressDTO> response = new SuccessResponse<>();
        response.setData(address);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Void>> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        addressService.createAddress(addressDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Address created successfully!");
        response.setStatus(HttpStatus.CREATED);
        response.setStatusCode(201);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessResponse<Void>> updateAddress(
        @PathVariable int id,
        @Valid @RequestBody AddressDTO addressDTO) {
        
        addressService.updateAddress(id, addressDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Address updated successfully!");
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessResponse<Void>> deleteAddress(@PathVariable int id) {
        addressService.deleteAddress(id);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Address deleted successfully!");
        response.setStatus(HttpStatus.NO_CONTENT);
        response.setStatusCode(204);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
