package group.fooddelivery.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.fooddelivery.main.entity.DeliveryDetails;

@Repository
public interface DeliveryDetailsRepository extends JpaRepository<DeliveryDetails, Integer> {
    
}
