package com.kull.controller;

import com.kull.dto.OrderLineDTO;
import com.kull.dto.ProductDTO;
import com.kull.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-line")
public class OrderLineController {

    @Autowired
    private OrderLineService orderLineService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrderLine(@RequestBody OrderLineDTO orderLine){
        orderLineService.create(orderLine);
        return new ResponseEntity<>(orderLine, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<OrderLineDTO> getAll(){
        return orderLineService.getAll();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update (@RequestBody OrderLineDTO orderLine){
        final OrderLineDTO updatedOrderLine = orderLineService.update(orderLine);
        return new ResponseEntity<>(updatedOrderLine,HttpStatus.OK);
    }

    @GetMapping("/all-products")
    public List<OrderLineDTO> getAllByProduct(@RequestBody ProductDTO product){
        return orderLineService.getAllByProduct(product);
    }
}
