package com.pereira.Controller;

import com.pereira.Entities.Node;
import com.pereira.Services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nodes")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @GetMapping
    public List<Node> getAllNodes() {
        return nodeService.getAllNodes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Node> getNodeById(@PathVariable Long id) {
        Optional<Node> node = nodeService.getNodeById(id);
        return node.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Node createNode(@RequestBody Node node) {
        return nodeService.saveNode(node);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Node> updateNode(@PathVariable Long id, @RequestBody Node node) {
        if (!nodeService.getNodeById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        node.setId(id); // Ensure the id is set
        return ResponseEntity.ok(nodeService.saveNode(node));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNode(@PathVariable Long id) {
        if (!nodeService.getNodeById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        nodeService.deleteNode(id);
        return ResponseEntity.noContent().build();
    }
}
