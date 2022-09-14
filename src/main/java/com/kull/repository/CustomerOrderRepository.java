package com.kull.repository;

import com.kull.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Long> {

    CustomerOrder findByOrderNumber(String orderNumber);
}
