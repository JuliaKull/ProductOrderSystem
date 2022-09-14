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

    @Autowired
    private OrderLineServiceImpl orderLineServiceImpl;

    @MockBean
    private WebMapper<ProductDTO, Product> webMapper1;

    @Mock
    private WebMapper<OrderLineDTO, OrderLine> webMapper;

    @Mock
    private WebMapper<ProductDTO, Product> webMapperProduct;

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


    /**
     * Method under test: {@link OrderLineServiceImpl#update(OrderLineDTO)}
     */
    @Test
    void testUpdate2() {

        Customer customer = new Customer();
        customer.setCustomerOrders(new ArrayList<>());
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setRegistrationCode(BigInteger.valueOf(42L));
        customer.setTelephone("4105551212");

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrder.setId(123L);
        customerOrder.setOrderNumber("42");
        customerOrder.setSubmissionDate(null);

        Product product = new Product();
        product.setId(123L);
        product.setName("Name");
        product.setOrderLines(new ArrayList<>());
        product.setSkuCode(1);
        product.setUnitPrice(1);

        OrderLine orderLine = new OrderLine();
        orderLine.setCustomerOrder(customerOrder);
        orderLine.setId(123L);
        orderLine.setProduct(product);
        orderLine.setQuantity(1);
        when(orderLineRepository.findByCustomerOrder((Integer) org.mockito.Mockito.any())).thenReturn(orderLine);
        orderLineServiceImpl.update(null);
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