package com.pereira.Repository;

import com.pereira.Entities.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Long> {
    // You can add custom query methods if needed
}
