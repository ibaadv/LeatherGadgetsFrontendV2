package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ReturnRequestService {

    @Autowired
    private ReturnRequestRepository repository;

    @Autowired
    private OrderRepository orderRepository;

    public ReturnRequest save(ReturnRequest request) {
        return repository.save(request);
    }

    public List<ReturnRequest> getAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void updateStatus(Long id, String status) {
        ReturnRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Return request not found with id: " + id));
        request.setStatus(status);
        repository.save(request);
    }

    public void updateStatusWithImage(Long id, String status, String reason, String deliveryBoyMobile, MultipartFile photo) throws IOException {
        ReturnRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Return request not found with id: " + id));

        request.setStatus(status);
        request.setReason(reason);
        request.setDeliveryBoyMobile(deliveryBoyMobile);

        if (photo != null && !photo.isEmpty()) {
            String filename = "return_" + id + ".jpg";
            Path path = Paths.get("uploads/" + filename);
            Files.createDirectories(path.getParent());
            Files.write(path, photo.getBytes());
            request.setPhotoUrl("/uploads/" + filename);
        }

        repository.save(request);
    }

    public void approveReturn(Long id) {
        ReturnRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Return request not found with id: " + id));

        request.setStatus("Approved");

        if (request.getOrderId() != null) {
            Order order = orderRepository.findById(request.getOrderId())
                    .orElseThrow(() -> new RuntimeException("Order not found for return request"));

            if (order.getAssignedDeliveryBoy() != null) {
                request.setDeliveryBoyMobile(order.getAssignedDeliveryBoy().getMobile());
            }
        }

        repository.save(request);
    }
}
