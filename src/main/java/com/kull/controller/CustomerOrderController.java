package com.kull.controller;

import com.kull.dto.*;
import com.kull.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.List;

@RequestMapping("/customer-order")
@RestController
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping("/create")
    public ResponseEntity<?>create(@Valid @RequestBody CustomerOrderDTO customerOrder){
        customerOrderService.create(customerOrder);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<CustomerOrderDTO> getAll(){
        return customerOrderService.getAll();
    }

    @GetMapping("/all-customers")
    public List<CustomerOrderDTO> getAllByCustomer(@RequestBody CustomerDTO customer){
        return customerOrderService.findAllByCustomer(customer);
    }

    @GetMapping("/all-date")
    public List<CustomerOrderDTO> getAllBySubmissionDate(@RequestBody ZonedDateTime submissionDate){
        return customerOrderService.findAllBySubmissionDate(submissionDate);
    }


}
