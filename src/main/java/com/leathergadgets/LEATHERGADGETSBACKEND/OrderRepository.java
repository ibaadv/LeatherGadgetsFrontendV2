package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByBuyerMobile(String buyerMobile);

    List<Order> findByAssignedDeliveryBoyMobile(String phone);
}