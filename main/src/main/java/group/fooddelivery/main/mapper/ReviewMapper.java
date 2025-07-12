package group.fooddelivery.main.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import group.fooddelivery.main.dto.ReviewDTO;
import group.fooddelivery.main.entity.Review;

@Component
public class ReviewMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
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

    public ReviewDTO toReview(Review review) {
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
}
