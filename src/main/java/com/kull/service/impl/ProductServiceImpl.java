package com.kull.service.impl;

import com.kull.dto.ProductDTO;
import com.kull.mapper.WebMapper;
import com.kull.model.Product;
import com.kull.repository.ProductRepository;
import com.kull.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private WebMapper<ProductDTO, Product> webMapper;


    @Override
    public void create(ProductDTO product) {
        repository.save(webMapper.toEntity(product));
    }

    @Override
    public ProductDTO update(ProductDTO product) {

        final Integer skuCode = product.getSkuCode();
        final Product productFromDb = repository.findBySkuCode(skuCode);

        if(productFromDb ==null){
            String message = "Product with Sku Code = " + skuCode + "does not exist.";
            throw new RuntimeException(message);
        }
        productFromDb.setUnitPrice(product.getUnitPrice());

        return webMapper.toDTO(productFromDb);
    }

    @Override
    public List<ProductDTO> getAll() {
        return webMapper.toDtos(repository.findAll());
    }

    @Override
    public void delete(ProductDTO product) {
        repository.delete(webMapper.toEntity(product));
    }
}
