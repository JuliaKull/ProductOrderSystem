package com.kull.service;

import com.kull.dto.CustomerOrderDTO;
import com.kull.dto.CustomerOrdersDTO;

import java.util.List;

public interface CustomerOrderService {

    void create(CustomerOrderDTO customerOrder);

    void createAll(CustomerOrdersDTO customerOrderDtos);

    List<CustomerOrderDTO> getAll();
}
