package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryNotificationService {

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    private WhatsAppService whatsAppService;

    public void notifyDeliveryBoy(Order order) {
        if (order == null) {
            System.err.println("❌ Order is null. Cannot send WhatsApp notification.");
            return;
        }

        String pinCode = order.getPinCode();
        if (pinCode == null || pinCode.trim().length() < 2) {
            System.err.println("❌ Invalid pinCode: " + pinCode);
            return;
        }

        String lastTwoDigits = pinCode.trim().substring(pinCode.trim().length() - 2);
        System.out.println("🔍 Looking for delivery person with pin ending in: " + lastTwoDigits);

        List<DeliveryPerson> deliveryPeople = deliveryPersonRepository.findAll();

        for (DeliveryPerson person : deliveryPeople) {
            if (person.getPinCode() != null && person.getPinCode().endsWith(lastTwoDigits)) {
                String mobile = person.getMobile();
                if (mobile == null || mobile.trim().isEmpty()) {
                    System.err.println("⚠️ Delivery person has no valid mobile number.");
                    continue;
                }

                String message = String.format(
                        "🛍️ *New Order Alert!*\n\n📦 Product: %s\n💰 Price: ₹%s\n📍 Address: %s",
                        defaultIfNull(order.getProductName()),
                        order.getPrice() != null ? order.getPrice().toString() : "N/A",
                        defaultIfNull(order.getDeliveryAddress())
                );

                System.out.println("📲 Sending WhatsApp message to " + mobile);
                boolean sent = whatsAppService.sendMessageWithImage(
                        mobile,
                        message,
                        order.getMainImage(),
                        order.getSellerQRCode()
                );

                if (sent) {
                    System.out.println("✅ Message successfully sent to: " + mobile);
                } else {
                    System.err.println("❌ Message failed to send to: " + mobile);
                }

                break; // Notify only one delivery person
            }
        }
    }

    private String defaultIfNull(String input) {
        return input != null ? input : "N/A";
    }
}
