package group.fooddelivery.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import group.fooddelivery.main.dto.ReviewDTO;
import group.fooddelivery.main.dto.response.SuccessResponse;
import group.fooddelivery.main.service.ReviewService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/reviews")
@Validated
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<ReviewDTO>>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getCategories(); // consider renaming to getAllReviews()

        SuccessResponse<List<ReviewDTO>> response = new SuccessResponse<>();
        response.setData(reviews);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<SuccessResponse<ReviewDTO>> getReviewById(@PathVariable int reviewId) {
        ReviewDTO review = reviewService.getReview(reviewId);

        SuccessResponse<ReviewDTO> response = new SuccessResponse<>();
        response.setData(review);
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Void>> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        reviewService.createReview(reviewDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Review created successfully!");
        response.setStatus(HttpStatus.CREATED);
        response.setStatusCode(201);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{reviewId}")
    public ResponseEntity<SuccessResponse<Void>> updateReview(
        @PathVariable int reviewId,
        @Valid @RequestBody ReviewDTO reviewDTO) {

        reviewService.updateReview(reviewId, reviewDTO);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Review updated successfully!");
        response.setStatus(HttpStatus.OK);
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<SuccessResponse<Void>> deleteReview(@PathVariable int reviewId) {
        reviewService.deleteReview(reviewId);

        SuccessResponse<Void> response = new SuccessResponse<>();
        response.setMessage("Review deleted successfully!");
        response.setStatus(HttpStatus.NO_CONTENT);
        response.setStatusCode(204);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
