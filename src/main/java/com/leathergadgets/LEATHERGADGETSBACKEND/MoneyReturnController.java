package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/money-returns")
@CrossOrigin("*")
public class MoneyReturnController {

    @Autowired
    private MoneyReturnService service;

    @PostMapping
    public ResponseEntity<MoneyReturn> create(@RequestBody MoneyReturn request) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping
    public ResponseEntity<List<MoneyReturn>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/mobile/{mobile}")
    public ResponseEntity<List<MoneyReturn>> getByMobile(@PathVariable String mobile) {
        return ResponseEntity.ok(service.getByBuyerMobile(mobile));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<MoneyReturn>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(service.getByStatus(status));
    }

    @PutMapping("/{id}/paid")
    public ResponseEntity<String> markAsPaid(@PathVariable Long id) {
        service.markAsPaid(id);
        return ResponseEntity.ok("Marked as Paid");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
