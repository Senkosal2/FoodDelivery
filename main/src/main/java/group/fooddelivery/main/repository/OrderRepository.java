package group.fooddelivery.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.fooddelivery.main.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}
