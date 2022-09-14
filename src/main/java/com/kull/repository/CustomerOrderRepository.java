package com.kull.repository;

import com.kull.model.Customer;
import com.kull.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Long> {

    List<CustomerOrder> findAllBySubmissionDate(ZonedDateTime submissionDate);
    List<CustomerOrder> findAllByCustomer(Customer customer);


}
