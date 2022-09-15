package com.kull.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineDTO {

    private ProductDTO product;

    private Integer quantity;

    private Integer customerOrder;
}
