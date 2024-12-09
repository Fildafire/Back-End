package com.pereira.Repository;

import com.pereira.Entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm, Long> {
    // You can add custom query methods if needed
}
