// ✅ Updated ReturnRequestController.java
package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/returns")
@CrossOrigin("*")
public class ReturnRequestController {

    @Autowired
    private ReturnRequestService service;

    @PostMapping
    public ReturnRequest create(@RequestBody ReturnRequest request) {
        request.setStatus("Pending");
        return service.save(request);
    }

    @GetMapping
    public List<ReturnRequest> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<String> approve(@PathVariable Long id) {
        service.approveReturn(id);
        return ResponseEntity.ok("Return request approved and assigned to delivery boy.");
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<String> reject(@PathVariable Long id) {
        service.updateStatus(id, "Rejected");
        return ResponseEntity.ok("Return request rejected.");
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateReturnWithImage(
            @PathVariable Long id,
            @RequestParam("status") String status,
            @RequestParam("reason") String reason,
            @RequestParam("deliveryBoyMobile") String deliveryBoyMobile,
            @RequestPart(value = "photo", required = false) MultipartFile photo
    ) {
        try {
            service.updateStatusWithImage(id, status, reason, deliveryBoyMobile, photo);
            return ResponseEntity.ok("Return updated with status and image.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
