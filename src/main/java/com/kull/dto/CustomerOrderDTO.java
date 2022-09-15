package com.kull.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOrderDTO {

    private CustomerDTO customer;

    private String orderNumber;

    private List<OrderLineDTO> orderLines;
}
