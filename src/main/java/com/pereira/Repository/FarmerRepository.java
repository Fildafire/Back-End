package com.pereira.Repository;

import com.pereira.Entities.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    // You can add custom query methods if needed
}
