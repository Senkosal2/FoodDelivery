package group.fooddelivery.main.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group.fooddelivery.main.dto.ReviewDTO;
import group.fooddelivery.main.entity.Review;
import group.fooddelivery.main.exception.NotFoundException;
import group.fooddelivery.main.mapper.FoodMapper;
import group.fooddelivery.main.mapper.ReviewMapper;
import group.fooddelivery.main.mapper.UserMapper;
import group.fooddelivery.main.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private UserMapper userMapper;
    
    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public ReviewDTO getReview(int reviewId) {
        Review existingReview = reviewRepository.findById(reviewId).orElseThrow(
            () -> new NotFoundException("Review does not exist!")
        );
        return reviewMapper.toReviewDTO(existingReview);
    }

    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public List<ReviewDTO> getCategories() {
        List<Review> allExistingCategories = reviewRepository.findAll();
        if (allExistingCategories.isEmpty()) {
            throw new NotFoundException("No categories!");
        }
        return reviewMapper.toReviewDTOs(allExistingCategories);
    }

    @Transactional // ensure db safely in case of error
    public void createReview(ReviewDTO reviewDTO) {
        Review review = reviewMapper.toReview(reviewDTO);
        reviewRepository.save(review);
    }

    @Transactional
    public void updateReview(int reviewId, ReviewDTO reviewDTO) {
        Review existingReview = reviewRepository.findById(reviewId).orElseThrow(
            () -> new NotFoundException("Review does not exist!")
        );
        existingReview.setComments(reviewDTO.getComments());
        existingReview.setRating(reviewDTO.getRating());
        existingReview.setFood(foodMapper.toFood(reviewDTO.getFoodDTO()));
        existingReview.setUser(userMapper.toUser(reviewDTO.getUserDTO()));

        existingReview.setReviewDate(reviewDTO.getReviewDate() == null 
        ? Date.valueOf(LocalDate.now())
        : reviewDTO.getReviewDate());

        existingReview.setReviewTime(reviewDTO.getReviewTime() == null
        ? Time.valueOf(LocalTime.now())
        : reviewDTO.getReviewTime());

        reviewRepository.save(existingReview);
    }

    @Transactional
    public void deleteReview(int reviewId) {
        Review existingReview = reviewRepository.findById(reviewId).orElseThrow(
            () -> new NotFoundException("Review does not exist!")
        );
        reviewRepository.delete(existingReview);
    }
}
