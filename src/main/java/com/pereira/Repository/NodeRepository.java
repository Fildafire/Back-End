package com.pereira.Repository;

import com.pereira.Entities.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<Node, Long> {
    // You can add custom query methods if needed
}
