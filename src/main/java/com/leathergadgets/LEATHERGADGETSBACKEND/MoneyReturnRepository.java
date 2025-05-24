package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MoneyReturnRepository extends JpaRepository<MoneyReturn, Long> {
    List<MoneyReturn> findByBuyerMobile(String buyerMobile);
    List<MoneyReturn> findByStatus(String status);
}
