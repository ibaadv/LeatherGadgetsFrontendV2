package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReturnRequestRepository extends JpaRepository<ReturnRequest, Long> {
    List<ReturnRequest> findByDeliveryBoyMobileAndStatus(String mobile, String status);
}
