package com.kull.service;

import com.kull.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    void create(CustomerDTO customer);

    CustomerDTO update(CustomerDTO customer);

    List<CustomerDTO> getAll();

    void delete(String email);


}
