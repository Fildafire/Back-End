package com.pereira.Controller;

import com.pereira.Entities.Data;
import com.pereira.Services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;  // Import the correct ResponseEntity
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping
    public List<Data> getAllData() {
        return dataService.getAllData();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Data> getDataById(@PathVariable Long id) {
        Optional<Data> data = dataService.getDataById(id);
        return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Data createData(@RequestBody Data data) {
        return dataService.saveData(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Data> updateData(@PathVariable Long id, @RequestBody Data data) {
        if (!dataService.getDataById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        data.setId(id); // Ensure the id is set
        return ResponseEntity.ok(dataService.saveData(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        if (!dataService.getDataById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        dataService.deleteData(id);
        return ResponseEntity.noContent().build();
    }
}
