package group.fooddelivery.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.fooddelivery.main.entity.Topping;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Integer>{
    
}
