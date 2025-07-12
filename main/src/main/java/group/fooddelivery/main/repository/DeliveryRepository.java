package group.fooddelivery.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.fooddelivery.main.entity.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer>{
    
}
