package com.kull.service;

import com.kull.dto.OrderLineDTO;
import com.kull.dto.ProductDTO;
import com.kull.model.OrderLine;
import com.kull.model.Product;

import java.util.List;

public interface OrderLineService {

    void create(OrderLineDTO orderLine);

    OrderLineDTO update (OrderLineDTO orderLine);

    List<OrderLineDTO> getAll();

    List<OrderLineDTO> getAllByProduct(ProductDTO product);
}
