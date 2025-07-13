package group.fooddelivery.main.mapper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import group.fooddelivery.main.dto.ReviewDTO;
import group.fooddelivery.main.entity.Review;

@Component
public class ReviewMapper {

    @Autowired
    @Lazy
    private UserMapper userMapper;

    @Autowired
    @Lazy
    private FoodMapper foodMapper;

    public Review toReview(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setComments(reviewDTO.getComments());
        review.setRating(reviewDTO.getRating());
        review.setReviewDate(reviewDTO.getReviewDate());
        review.setReviewTime(reviewDTO.getReviewTime());
        review.setUser(userMapper.toUser(reviewDTO.getUserDTO()));
        review.setFood(foodMapper.toFood(reviewDTO.getFoodDTO()));

        return review;
    }

    public List<Review> toReviews(List<ReviewDTO> reviewDTOs) {
        List<Review> reviews = new ArrayList<>();
        for (ReviewDTO reviewDTO : reviewDTOs) {
            reviews.add(toReview(reviewDTO));
        }
        return reviews;
    }

    public ReviewDTO toReviewDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setComments(review.getComments());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setReviewDate(review.getReviewDate());
        reviewDTO.setReviewTime(review.getReviewTime());
        reviewDTO.setUserDTO(userMapper.toUserDTO(review.getUser()));
        reviewDTO.setFoodDTO(foodMapper.toFoodDTO(review.getFood()));

        return reviewDTO;
    }

    public List<ReviewDTO> toReviewDTOs(List<Review> reviews) {
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        for (Review review : reviews) {
            reviewDTOs.add(toReviewDTO(review));
        }
        return reviewDTOs;
    }
}
