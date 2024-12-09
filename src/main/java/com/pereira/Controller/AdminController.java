package com.pereira.Controller;

import com.pereira.Entities.Admin;
import com.pereira.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Optional<Admin> admin = adminService.getAdminById(id);
        return admin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        if (!adminService.getAdminById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        admin.setId(id); // Ensure the id is set
        return ResponseEntity.ok(adminService.saveAdmin(admin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        if (!adminService.getAdminById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
