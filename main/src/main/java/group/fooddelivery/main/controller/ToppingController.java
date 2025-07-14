package group.fooddelivery.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import group.fooddelivery.main.dto.ToppingDTO;
import group.fooddelivery.main.dto.response.SuccessResponse;
import group.fooddelivery.main.service.ToppingService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/toppings")
@Validated
public class ToppingController {

    @Autowired
    private ToppingService toppingService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<ToppingDTO>>> getAllToppings() {
        List<ToppingDTO> toppings = toppingService.getCategories(); // consider renaming to getAllToppings()

        SuccessResponse<List<ToppingDTO>> response = new SuccessResponse<>();
        response.setData(toppings);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{toppingId}")
    public ResponseEntity<SuccessResponse<ToppingDTO>> getToppingById(@PathVariable int toppingId) {
        ToppingDTO topping = toppingService.getTopping(toppingId);

        SuccessResponse<ToppingDTO> response = new SuccessResponse<>();
        response.setData(topping);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Void>> createTopping(@Valid @RequestBody ToppingDTO toppingDTO) {
        toppingService.createTopping(toppingDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Topping created successfully!");
        response.setStatus(HttpStatus.CREATED);
        response.setStatusCode(201);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{toppingId}")
    public ResponseEntity<SuccessResponse<Void>> updateTopping(
        @PathVariable int toppingId,
        @Valid @RequestBody ToppingDTO toppingDTO) {

        toppingService.updateTopping(toppingId, toppingDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Topping updated successfully!");
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{toppingId}")
    public ResponseEntity<SuccessResponse<Void>> deleteTopping(@PathVariable int toppingId) {
        toppingService.deleteTopping(toppingId);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Topping deleted successfully!");
        response.setStatus(HttpStatus.NO_CONTENT);
        response.setStatusCode(204);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
