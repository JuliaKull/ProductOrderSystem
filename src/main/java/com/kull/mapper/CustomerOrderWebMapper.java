package com.kull.mapper;

import com.kull.dto.CustomerOrderDTO;
import com.kull.model.CustomerOrder;
import org.springframework.stereotype.Component;

@Component
public class CustomerOrderWebMapper implements WebMapper<CustomerOrderDTO, CustomerOrder> {

    @Override
    public CustomerOrderDTO toDTO(CustomerOrder entity) {
        return CustomerOrderDTO.builder()
                .orderNumber(entity.getOrderNumber())
                .build();
    }

    @Override
    public CustomerOrder toEntity(CustomerOrderDTO dto) {
        return
                CustomerOrder.builder()
                        .orderNumber(dto.getOrderNumber())
                        .build();
    }
}
