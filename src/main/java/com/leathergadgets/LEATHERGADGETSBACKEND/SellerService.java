// ✅ SellerService.java
package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public Seller saveSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id).orElse(null);
    }

    public Seller updateSeller(Long id, Seller updatedSeller) {
        Seller existing = sellerRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setStoreName(updatedSeller.getStoreName());
            existing.setOwnerName(updatedSeller.getOwnerName());
            existing.setEmail(updatedSeller.getEmail());
            existing.setMobile(updatedSeller.getMobile());
            existing.setGst(updatedSeller.getGst());
            existing.setPan(updatedSeller.getPan());
            existing.setAddress(updatedSeller.getAddress());
            existing.setPincode(updatedSeller.getPincode());
            existing.setAccountNumber(updatedSeller.getAccountNumber());
            existing.setIfscCode(updatedSeller.getIfscCode());
            existing.setUpiId(updatedSeller.getUpiId());
            existing.setQrCodeImage(updatedSeller.getQrCodeImage());
            return sellerRepository.save(existing);
        }
        return null;
    }

    public boolean deleteSeller(Long id) {
        if (sellerRepository.existsById(id)) {
            sellerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
