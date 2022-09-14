package com.kull.service.impl;

import com.kull.dto.OrderLineDTO;
import com.kull.mapper.WebMapper;
import com.kull.model.OrderLine;
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
    private OrderLineRepository orderLineRepository;

    @Override
    public void create(OrderLineDTO orderLine) {
        final OrderLine entity = webMapper.toEntity(orderLine);
        orderLineRepository.save(entity);
    }

    @Override
    public OrderLineDTO update(OrderLineDTO orderLine) {
        return ;
    }

    @Override
    public List<OrderLineDTO> getAll() {
        return webMapper.toDtos(orderLineRepository.findAll());
    }
}
