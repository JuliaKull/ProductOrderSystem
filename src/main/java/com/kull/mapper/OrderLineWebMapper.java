package com.kull.mapper;

import com.kull.dto.OrderLineDTO;
import com.kull.model.OrderLine;
import org.springframework.stereotype.Component;

@Component
public class OrderLineWebMapper implements WebMapper<OrderLineDTO,OrderLine> {


    @Override
    public OrderLineDTO toDTO(OrderLine entity) {
        return OrderLineDTO.builder()
                .quantity(entity.getQuantity())
                .build();
    }

    @Override
    public OrderLine toEntity(OrderLineDTO dto) {
        return OrderLine.builder()
                .quantity(dto.getQuantity())
                .build();
    }
}
