package com.kull.service.impl;

import com.kull.dto.CustomerOrderDTO;
import com.kull.dto.OrderLineDTO;
import com.kull.dto.ProductDTO;
import com.kull.mapper.WebMapper;
import com.kull.model.CustomerOrder;
import com.kull.model.OrderLine;
import com.kull.model.Product;
import com.kull.repository.OrderLineRepository;
import com.kull.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
    private WebMapper<OrderLineDTO, OrderLine> webMapper;

    @Autowired
    private WebMapper<ProductDTO, Product> webMapperProduct;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Override
    public void create(OrderLineDTO orderLine) {
        final OrderLine entity = webMapper.toEntity(orderLine);
        orderLineRepository.save(entity);
    }

    @Override
    public OrderLineDTO update(OrderLineDTO orderLine) {
        OrderLine customerOrderFromDb = orderLineRepository.findByCustomerOrder(orderLine.getCustomerOrder());

        if (customerOrderFromDb == null) {
            String message = "Customer Order does not exist.";
            throw new RuntimeException(message);
        }
        customerOrderFromDb.setQuantity(orderLine.getQuantity());

        return webMapper.toDTO(customerOrderFromDb);
    }

    @Override
    public List<OrderLineDTO> getAll() {
        return webMapper.toDtos(orderLineRepository.findAll());
    }

    @Override
    public List<OrderLineDTO> getAllByProduct(ProductDTO product) {
        final Product productEntity = webMapperProduct.toEntity(product);
        return webMapper.toDtos(orderLineRepository.findAllByProduct(productEntity));
    }
}
