package com.pereira.Services;

import com.pereira.Entities.Node;
import com.pereira.Repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    public List<Node> getAllNodes() {
        return nodeRepository.findAll();
    }

    public Optional<Node> getNodeById(Long id) {
        return nodeRepository.findById(id);
    }

    public Node saveNode(Node node) {
        return nodeRepository.save(node);
    }

    public void deleteNode(Long id) {
        nodeRepository.deleteById(id);
    }
}
