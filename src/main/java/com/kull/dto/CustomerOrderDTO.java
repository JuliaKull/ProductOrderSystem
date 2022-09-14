package com.kull.dto;

import com.kull.model.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

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
