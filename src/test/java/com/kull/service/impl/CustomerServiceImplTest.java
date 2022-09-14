package com.kull.service.impl;

import com.kull.dto.CustomerDTO;
import com.kull.dto.CustomerOrderDTO;
import com.kull.mapper.WebMapper;
import com.kull.model.Customer;
import com.kull.model.CustomerOrder;
import com.kull.repository.CustomerRepository;
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
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private WebMapper<CustomerDTO, Customer> webMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;



    @Test
    void createCustomer() {
        Customer customer = new Customer();
        when(repository.save((Customer) any())).thenReturn(customer);
        when(webMapper.toEntity((CustomerDTO) any())).thenReturn(customer);
        customerService.create(mock(CustomerDTO.class));
        verify(repository).save((Customer) any());
        verify(webMapper).toEntity((CustomerDTO) any());

    }

    @Test
    void updateCustomer() {
        Customer customer = new Customer();
        customer.setEmail("test@test.com");
        when(repository.findByEmail((String) any())).thenReturn(customer);
        when(webMapper.toDTO((Customer) any())).thenReturn(mock(CustomerDTO.class));
        CustomerDTO customerDTO = mock(CustomerDTO.class);
        when(customerDTO.getEmail()).thenReturn("test2@test.com");
        customerService.update(customerDTO);
        verify(repository).findByEmail((String) any());
    }

    @Test
    void getAllCustomersFromDb() {
        ArrayList<CustomerDTO> customerDTOList = new ArrayList<>();
        when(webMapper.toDtos((List<Customer>) any())).thenReturn(customerDTOList);
        List<CustomerDTO> actualAll = customerService.getAll();
        assertSame(customerDTOList, actualAll);
        verify(webMapper).toDtos((List<Customer>) any());
        verify(repository).findAll();


    }

    @Test
    void deleteCustomerFromDb() {
        Customer customer = new Customer();
        customer.setEmail("test@test.com");
        when(repository.deleteByEmail((String) any())).thenReturn(customer);
        customerService.delete("test@test.com");
        verify(repository).deleteByEmail((String) any());

    }
}