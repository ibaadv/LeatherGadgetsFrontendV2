package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
@CrossOrigin(origins = "*")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping
    public Seller createSeller(@RequestBody Seller seller) {
        return sellerService.saveSeller(seller);
    }

    @GetMapping
    public List<Seller> getAllSellers() {
        return sellerService.getAllSellers();
    }

    @GetMapping("/{id}")
    public Seller getSellerById(@PathVariable Long id) {
        return sellerService.getSellerById(id);
    }

    @PutMapping("/{id}")
    public Seller updateSeller(@PathVariable Long id, @RequestBody Seller updated) {
        return sellerService.updateSeller(id, updated);
    }

    @DeleteMapping("/{id}")
    public boolean deleteSeller(@PathVariable Long id) {
        return sellerService.deleteSeller(id);
    }
}
