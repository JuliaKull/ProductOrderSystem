package com.kull.service.impl;

import com.kull.dto.CustomerDTO;
import com.kull.dto.CustomerOrderDTO;
import com.kull.mapper.WebMapper;
import com.kull.model.Customer;
import com.kull.model.CustomerOrder;
import com.kull.repository.CustomerOrderRepository;
import com.kull.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;


@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private WebMapper<CustomerOrderDTO, CustomerOrder> mapper;
    @Autowired
    private WebMapper<CustomerDTO, Customer> mapperCustomer;
    @Autowired
    private CustomerOrderRepository repository;



    @Override
    public void create(CustomerOrderDTO customerOrder) {
        final var orderToCreate = mapper.toEntity(customerOrder);
        repository.save(orderToCreate);
    }

    @Override
    public List<CustomerOrderDTO> getAll() {
        return mapper.toDtos(repository.findAll());
    }

    @Override
    public List<CustomerOrderDTO> findAllBySubmissionDate(ZonedDateTime submissionDate) {
        return mapper.toDtos(repository.findAllBySubmissionDate(submissionDate));
    }

    @Override
    public List<CustomerOrderDTO> findAllByCustomer(CustomerDTO customer) {
        Customer customerEntity = mapperCustomer.toEntity(customer);
        return mapper.toDtos(repository.findAllByCustomer(customerEntity));
    }


}
