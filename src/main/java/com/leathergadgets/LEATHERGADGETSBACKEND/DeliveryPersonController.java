// Updated DeliveryPersonController.java
package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@CrossOrigin(origins = "*")
public class DeliveryPersonController {

    @Autowired
    private DeliveryPersonService deliveryPersonService;

    @PostMapping("/register")
    public ResponseEntity<String> registerDeliveryPerson(
            @RequestPart("person") String personJson,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        try {
            deliveryPersonService.register(personJson, image);
            return ResponseEntity.ok("✅ Delivery person registered successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Registration failed: " + e.getMessage());
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrdersByPhone(@RequestParam String phone) {
        List<Order> orders = deliveryPersonService.getOrdersByPhone(phone);
        return ResponseEntity.ok(orders);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryPerson>> getAllDeliveryPersons() {
        return ResponseEntity.ok(deliveryPersonService.getAllDeliveryPersons());
    }

    @PutMapping("/{id}")
    public DeliveryPerson updateDeliveryPerson(@PathVariable Long id, @RequestBody DeliveryPerson updated) {
        return deliveryPersonService.updateDeliveryPerson(id, updated);
    }

    @DeleteMapping("/{id}")
    public boolean deleteDeliveryPerson(@PathVariable Long id) {
        return deliveryPersonService.deleteDeliveryPerson(id);
    }
}