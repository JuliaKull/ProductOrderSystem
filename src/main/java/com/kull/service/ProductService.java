package com.kull.service;

import com.kull.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    void create(ProductDTO product);

    ProductDTO update(ProductDTO product);

    List<ProductDTO> getAll();

    void delete(ProductDTO product);
}
