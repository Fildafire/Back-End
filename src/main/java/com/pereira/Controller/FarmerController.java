package com.pereira.Controller;

import com.pereira.Entities.Farmer;
import com.pereira.Services.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/farmers")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @GetMapping
    public List<Farmer> getAllFarmers() {
        return farmerService.getAllFarmers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farmer> getFarmerById(@PathVariable Long id) {
        Optional<Farmer> farmer = farmerService.getFarmerById(id);
        return farmer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Farmer createFarmer(@RequestBody Farmer farmer) {
        return farmerService.saveFarmer(farmer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farmer> updateFarmer(@PathVariable Long id, @RequestBody Farmer farmer) {
        if (!farmerService.getFarmerById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        farmer.setId(id); // Ensure the id is set
        return ResponseEntity.ok(farmerService.saveFarmer(farmer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable Long id) {
        if (!farmerService.getFarmerById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        farmerService.deleteFarmer(id);
        return ResponseEntity.noContent().build();
    }
}
