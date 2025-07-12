package group.fooddelivery.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.fooddelivery.main.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer>{
    
}
