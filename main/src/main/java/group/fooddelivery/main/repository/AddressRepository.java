package group.fooddelivery.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.fooddelivery.main.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    
}
