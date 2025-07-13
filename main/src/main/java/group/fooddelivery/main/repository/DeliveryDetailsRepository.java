package group.fooddelivery.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.fooddelivery.main.entity.DeliveryDetail;

@Repository
public interface DeliveryDetailsRepository extends JpaRepository<DeliveryDetail, Integer> {
    
}
