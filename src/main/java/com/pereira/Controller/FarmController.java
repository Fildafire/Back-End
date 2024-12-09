package com.pereira.Controller;

import com.pereira.Entities.Farm;
import com.pereira.Services.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/farms")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @GetMapping
    public List<Farm> getAllFarms() {
        return farmService.getAllFarms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farm> getFarmById(@PathVariable Long id) {
        Optional<Farm> farm = farmService.getFarmById(id);
        return farm.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Farm createFarm(@RequestBody Farm farm) {
        return farmService.saveFarm(farm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farm> updateFarm(@PathVariable Long id, @RequestBody Farm farm) {
        if (!farmService.getFarmById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        farm.setId(id); // Ensure the id is set
        return ResponseEntity.ok(farmService.saveFarm(farm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        if (!farmService.getFarmById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        farmService.deleteFarm(id);
        return ResponseEntity.noContent().build();
    }
}
