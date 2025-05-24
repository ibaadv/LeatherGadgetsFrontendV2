package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeliveryPersonRepository deliveryBoyRepository;

    @Autowired
    private DeliveryNotificationService deliveryNotificationService;

    public Order save(Order order) {
        order.setId(null);

        String pinCode = order.getPinCode();
        if (pinCode != null && pinCode.length() >= 2) {
            String lastTwoDigits = pinCode.substring(pinCode.length() - 2);
            List<DeliveryPerson> deliveryBoys = deliveryBoyRepository.findAll();
            for (DeliveryPerson boy : deliveryBoys) {
                if (boy.getPinCode() != null &&
                        boy.getPinCode().length() >= 2 &&
                        boy.getPinCode().endsWith(lastTwoDigits)) {
                    order.setAssignedDeliveryBoy(boy);
                    break;
                }
            }
        }

        if (order.getAssignedDeliveryBoy() == null) {
            throw new RuntimeException("No delivery boy found for pin code ending with " +
                    pinCode.substring(pinCode.length() - 2));
        }

        Order savedOrder = orderRepository.save(order);
        deliveryNotificationService.notifyDeliveryBoy(savedOrder);
        return savedOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrdersByBuyerMobile(String buyerMobile) {
        return orderRepository.findByBuyerMobile(buyerMobile);
    }

    public Order updateStatus(Long id, String status) {
        Order existingOrder = getOrderById(id);
        if (existingOrder == null) {
            throw new RuntimeException("Order not found with ID: " + id);
        }
        existingOrder.setStatus(status);
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with ID: " + id);
        }
        orderRepository.deleteById(id);
    }
}
