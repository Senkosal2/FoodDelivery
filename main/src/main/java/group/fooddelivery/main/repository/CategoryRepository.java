package group.fooddelivery.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.fooddelivery.main.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
