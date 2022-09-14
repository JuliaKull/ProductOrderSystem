package com.kull.service;

import com.kull.dto.CustomerDTO;
import com.kull.dto.CustomerOrderDTO;
import com.kull.dto.CustomerOrdersDTO;
import com.kull.model.Customer;
import com.kull.model.CustomerOrder;

import java.time.ZonedDateTime;
import java.util.List;

public interface CustomerOrderService {

    void create(CustomerOrderDTO customerOrder);

    List<CustomerOrderDTO> getAll();

    List<CustomerOrderDTO> findAllBySubmissionDate(ZonedDateTime submissionDate);
    List<CustomerOrderDTO> findAllByCustomer(CustomerDTO customer);



}
