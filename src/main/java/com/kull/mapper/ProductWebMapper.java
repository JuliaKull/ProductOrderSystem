package com.kull.mapper;

import com.kull.dto.ProductDTO;
import com.kull.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductWebMapper implements WebMapper<ProductDTO, Product> {
    @Override
    public ProductDTO toDTO(Product entity) {
        return ProductDTO.builder()
                .name(entity.getName())
                .skuCode(entity.getSkuCode())
                .unitPrice(entity.getUnitPrice())
                .orderLineCount(entity.getOrderLines().size())
                .build();
    }

    @Override
    public Product toEntity(ProductDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .skuCode(dto.getSkuCode())
                .unitPrice(dto.getUnitPrice())
                .build();
    }
}
