package com.kull.service.impl;

import com.kull.dto.CustomerDTO;
import com.kull.dto.CustomerOrderDTO;
import com.kull.dto.OrderLineDTO;
import com.kull.dto.ProductDTO;
import com.kull.mapper.WebMapper;
import com.kull.model.Customer;
import com.kull.model.CustomerOrder;
import com.kull.model.OrderLine;
import com.kull.model.Product;
import com.kull.repository.OrderLineRepository;

import java.math.BigInteger;

import org.junit.jupiter.api.Disabled;
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

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderLineServiceImplTest {


    @Mock
    private WebMapper<OrderLineDTO, OrderLine> webMapper;

    @Mock
    private OrderLineRepository orderLineRepository;

    @InjectMocks
    OrderLineServiceImpl orderLineService;


    @Test
    void createOrderLine() {
        Product product = new Product();
        OrderLine orderLine = OrderLine.builder()
                .product(product)
                .quantity(1)
                .build();
        when(webMapper.toEntity((OrderLineDTO) any())).thenReturn(orderLine);
        orderLineService.create(new OrderLineDTO());
        verify(orderLineRepository).save((OrderLine) any());
        verify(webMapper).toEntity((OrderLineDTO) any());
    }

    @Test
    void updateOrderLine() {
        Product product = Product.builder()
                .name("Test")
                .unitPrice(1)
                .skuCode(1)
                .orderLines(new ArrayList<>())
                .build();
      CustomerOrder customerOrder = CustomerOrder.builder()
                .customer(new Customer())
                .orderNumber("1")
                .submissionDate(ZonedDateTime.now())
                .build();
        OrderLine orderLine = OrderLine.builder()
                .customerOrder(customerOrder)
                .quantity(1)
                .product(product)
                .build();
        orderLineRepository.save(orderLine);
        when(orderLineRepository.findByCustomerOrder(any())).thenReturn(orderLine);
        orderLine.setQuantity(2);
        orderLineRepository.save(orderLine);
        verify(orderLineRepository).findByCustomerOrder(any());

    }


    @Test
    void getAllOrderLines() {
        when(orderLineRepository.findAll()).thenReturn(new ArrayList<>());
        ArrayList<OrderLineDTO> orderLineDTOList = new ArrayList<>();
        when(webMapper.toDtos((List<OrderLine>) any())).thenReturn(orderLineDTOList);
        List<OrderLineDTO> actualAll = orderLineService.getAll();
        assertSame(orderLineDTOList, actualAll);
        verify(orderLineRepository).findAll();
        verify(webMapper).toDtos((List<OrderLine>) any());
    }

    @Test
    void getAllByProduct() {
        Product product = new Product();
        OrderLine orderLine = OrderLine.builder()
                .product(product)
                .quantity(1)
                .build();
        when(orderLineRepository.findAllByProduct((Product) any())).thenReturn(new ArrayList<>());
        ArrayList<OrderLineDTO> orderLineDTOList = new ArrayList<>();
        when(webMapper.toDtos((List<OrderLine>) any())).thenReturn(orderLineDTOList);
        List<OrderLineDTO> actualAll = orderLineService.getAllByProduct(new ProductDTO());
        assertSame(orderLineDTOList, actualAll);
        verify(orderLineRepository).findAllByProduct((Product) any());

    }
}