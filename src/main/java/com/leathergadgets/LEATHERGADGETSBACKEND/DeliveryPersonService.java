package com.leathergadgets.LEATHERGADGETSBACKEND;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DeliveryPersonService {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void register(String personJson, MultipartFile image) throws IOException {
        DeliveryPerson person = objectMapper.readValue(personJson, DeliveryPerson.class);

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        if (image != null && !image.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            File destination = Paths.get(UPLOAD_DIR, fileName).toFile();
            image.transferTo(destination);
            String imageUrl = "/uploads/" + fileName;
            person.setProfileImageUrl(imageUrl);
        }

        deliveryPersonRepository.save(person);
        System.out.println("Delivery person saved: " + person.getName());
    }

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrdersByPhone(String phone) {
        return orderRepository.findByAssignedDeliveryBoyMobile(phone);
    }

    public List<DeliveryPerson> getAllDeliveryPersons() {
        return deliveryPersonRepository.findAll();
    }

    public DeliveryPerson updateDeliveryPerson(Long id, DeliveryPerson updatedPerson) {
        DeliveryPerson existing = deliveryPersonRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(updatedPerson.getName());
            existing.setMobile(updatedPerson.getMobile());
            existing.setEmail(updatedPerson.getEmail());
            existing.setArea(updatedPerson.getArea());
            existing.setPinCode(updatedPerson.getPinCode());
            existing.setVehicleNumber(updatedPerson.getVehicleNumber());
            existing.setVehicleType(updatedPerson.getVehicleType());
            existing.setProfileImageUrl(updatedPerson.getProfileImageUrl());
            return deliveryPersonRepository.save(existing);
        }
        return null;
    }

    public boolean deleteDeliveryPerson(Long id) {
        if (deliveryPersonRepository.existsById(id)) {
            deliveryPersonRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
