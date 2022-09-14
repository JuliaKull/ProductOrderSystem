package com.kull.controller;

import com.kull.dto.ProductDTO;
import com.kull.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<ProductDTO> getAll(){
        return productService.getAll();
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody ProductDTO product){
        final ProductDTO updatedProduct = productService.update(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductDTO product){
        productService.create(product);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<?> deleteProduct(@RequestBody ProductDTO product) {
        productService.delete(product);
        return ResponseEntity.ok(product);
    }
}
