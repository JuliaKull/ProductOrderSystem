package com.kull.service.impl;

import com.kull.dto.ProductDTO;
import com.kull.mapper.WebMapper;
import com.kull.model.Product;
import com.kull.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductServiceImplTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private WebMapper<ProductDTO, Product> webMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void createProduct() {
        Product product = new Product();
        when(webMapper.toEntity((ProductDTO) any())).thenReturn(product);
        productService.create(new ProductDTO());
        verify(repository).save((Product) any());
    }

    @Test
    void updateProduct() {
        Product product = mock(Product.class);
        product.setSkuCode(1);
        when(repository.findBySkuCode((Integer) any())).thenReturn(product);
        when(webMapper.toDTO((Product) any())).thenReturn(new ProductDTO());
        productService.update(new ProductDTO());
    }

    @Test
    void getAllProductsFromDb() {
        when(repository.findAll()).thenReturn(new ArrayList<>());
        ArrayList<ProductDTO> productDTOList = new ArrayList<>();
        when(webMapper.toDtos((List<Product>) any())).thenReturn(productDTOList);
        List<ProductDTO> actualAll = productService.getAll();
        assertSame(productDTOList, actualAll);
        verify(repository).findAll();
        verify(webMapper).toDtos((List<Product>) any());
    }

    @Test
    void deleteProduct() {
        Product product = new Product();
        when(webMapper.toEntity((ProductDTO) any())).thenReturn(product);
        productService.delete(new ProductDTO());
        verify(repository).delete((Product) any());
    }
}