package com.pereira.Repository;

import com.pereira.Entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    // You can add custom query methods if needed
}
