package com.pereira.Controller;

import com.pereira.Entities.Sensor;
import com.pereira.Services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public List<Sensor> getAllSensors() {
        return sensorService.getAllSensors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable Long id) {
        Optional<Sensor> sensor = sensorService.getSensorById(id);
        return sensor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sensor createSensor(@RequestBody Sensor sensor) {
        return sensorService.saveSensor(sensor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sensor> updateSensor(@PathVariable Long id, @RequestBody Sensor sensor) {
        if (!sensorService.getSensorById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        sensor.setId(id); // Ensure the id is set
        return ResponseEntity.ok(sensorService.saveSensor(sensor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long id) {
        if (!sensorService.getSensorById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        sensorService.deleteSensor(id);
        return ResponseEntity.noContent().build();
    }
}
