package com.kull.service.impl;

import com.kull.dto.CustomerDTO;
import com.kull.dto.CustomerOrderDTO;
import com.kull.mapper.WebMapper;
import com.kull.model.Customer;
import com.kull.model.CustomerOrder;
import com.kull.repository.CustomerOrderRepository;
import com.kull.repository.CustomerRepository;
import com.kull.repository.OrderLineRepository;
import com.kull.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerOrderServiceImplTest {

    @InjectMocks
    CustomerOrderServiceImpl customerOrderService;

    @Mock
    private WebMapper<CustomerOrderDTO, CustomerOrder> mapper;

    @Mock
    private WebMapper<CustomerDTO, Customer> mapperCustomer;

    @Mock
    private CustomerOrderRepository repository;


    @Test
    void createCustomerOrder() {
        CustomerOrder customerOrder = CustomerOrder.builder()
                .customer(new Customer())
                .orderNumber("1")
                .submissionDate(ZonedDateTime.now())
                .build();
        when(mapper.toEntity(any())).thenReturn(customerOrder);
        when(repository.save((CustomerOrder) any())).thenReturn(customerOrder);
        customerOrderService.create(new CustomerOrderDTO());
        verify(mapper).toEntity((CustomerOrderDTO) any());
        verify(repository).save((CustomerOrder) any());
    }


    @Test
    void getAllCustomerOrdersFromDb() {
        ArrayList<CustomerOrderDTO> customerOrderDTOList = new ArrayList<>();
        when(mapper.toDtos((List<CustomerOrder>) any())).thenReturn(customerOrderDTOList);
        List<CustomerOrderDTO> actualAll = customerOrderService.getAll();
        assertSame(customerOrderDTOList, actualAll);
        verify(mapper).toDtos((List<CustomerOrder>) any());
        verify(repository).findAll();
    }

    @Test
    void findAllBySubmissionDate() {
        CustomerOrder customerOrder = CustomerOrder.builder()
                .customer(new Customer())
                .orderNumber("1")
                .submissionDate(ZonedDateTime.now())
                .build();
        ArrayList<CustomerOrderDTO> customerOrderDTOList = new ArrayList<>();
        customerOrderDTOList.add(mapper.toDTO(customerOrder));
        when(mapper.toDtos((List<CustomerOrder>) any())).thenReturn(customerOrderDTOList);
        List<CustomerOrderDTO> actualAll = customerOrderService.findAllBySubmissionDate(customerOrder.getSubmissionDate());
        assertSame(customerOrderDTOList, actualAll);
        verify(mapper).toDtos((List<CustomerOrder>) any());
        verify(repository).findAllBySubmissionDate(customerOrder.getSubmissionDate());

    }

    @Test
    void findAllByCustomer() {
        Customer customer = new Customer();
        CustomerOrder customerOrder = CustomerOrder.builder()
                .customer(customer)
                .orderNumber("1")
                .submissionDate(ZonedDateTime.now())
                .build();
        when(mapperCustomer.toEntity((CustomerDTO) any())).thenReturn(customer);
        ArrayList<CustomerOrderDTO> customerOrderDTOList = new ArrayList<>();
        when(mapper.toDtos((List<CustomerOrder>) any())).thenReturn(customerOrderDTOList);
        when(repository.findAllByCustomer((Customer) any())).thenReturn(new ArrayList<>());;
        List<CustomerOrderDTO> actualAll = customerOrderService.findAllByCustomer(mock(CustomerDTO.class));
        assertSame(customerOrderDTOList, actualAll);
        verify(mapper).toDtos((List<CustomerOrder>) any());
        verify(repository).findAllByCustomer(customerOrder.getCustomer());

    }
}