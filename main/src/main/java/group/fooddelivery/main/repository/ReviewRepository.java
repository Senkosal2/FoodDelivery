package group.fooddelivery.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.fooddelivery.main.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
    
}
