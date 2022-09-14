package com.kull.service;

import com.kull.dto.OrderLineDTO;

import java.util.List;

public interface OrderLineService {

    void create(OrderLineDTO orderLine);

    OrderLineDTO update (OrderLineDTO orderLine);

    List<OrderLineDTO> getAll();
}
