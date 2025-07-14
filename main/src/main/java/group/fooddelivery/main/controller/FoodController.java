package group.fooddelivery.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import group.fooddelivery.main.dto.FoodDTO;
import group.fooddelivery.main.dto.response.SuccessResponse;
import group.fooddelivery.main.service.FoodService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/foods")
@Validated
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<FoodDTO>>> getAllFoods() {
        List<FoodDTO> foods = foodService.getCategories(); // consider renaming to getAllFoods()

        SuccessResponse<List<FoodDTO>> response = new SuccessResponse<>();
        response.setData(foods);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<SuccessResponse<FoodDTO>> getFoodById(@PathVariable int foodId) {
        FoodDTO food = foodService.getFood(foodId);

        SuccessResponse<FoodDTO> response = new SuccessResponse<>();
        response.setData(food);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Void>> createFood(@Valid @RequestBody FoodDTO foodDTO) {
        foodService.createFood(foodDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Food created successfully!");
        response.setStatus(HttpStatus.CREATED);
        response.setStatusCode(201);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{foodId}")
    public ResponseEntity<SuccessResponse<Void>> updateFood(
        @PathVariable int foodId,
        @Valid @RequestBody FoodDTO foodDTO) {

        foodService.updateFood(foodId, foodDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Food updated successfully!");
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{foodId}")
    public ResponseEntity<SuccessResponse<Void>> deleteFood(@PathVariable int foodId) {
        foodService.deleteFood(foodId);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Food deleted successfully!");
        response.setStatus(HttpStatus.NO_CONTENT);
        response.setStatusCode(204);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
