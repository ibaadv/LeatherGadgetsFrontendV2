package com.leathergadgets.LEATHERGADGETSBACKEND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MoneyReturnService {

    @Autowired
    private MoneyReturnRepository repository;

    public MoneyReturn save(MoneyReturn request) {
        return repository.save(request);
    }

    public List<MoneyReturn> getAll() {
        return repository.findAll();
    }

    public List<MoneyReturn> getByBuyerMobile(String mobile) {
        return repository.findByBuyerMobile(mobile);
    }

    public List<MoneyReturn> getByStatus(String status) {
        return repository.findByStatus(status);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void markAsPaid(Long id) {
        MoneyReturn moneyReturn = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Money return not found with id: " + id));
        moneyReturn.setStatus("Paid");
        repository.save(moneyReturn);
    }
}
