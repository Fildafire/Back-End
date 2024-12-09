package com.pereira.Repository;

import com.pereira.Entities.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    // You can add custom query methods if needed
}
