package com.kull.service.impl;

import com.kull.dto.CustomerDTO;
import com.kull.dto.CustomerOrderDTO;
import com.kull.dto.CustomerOrdersDTO;
import com.kull.dto.ProductDTO;
import com.kull.mapper.WebMapper;
import com.kull.model.Customer;
import com.kull.model.CustomerOrder;
import com.kull.model.OrderLine;
import com.kull.model.Product;
import com.kull.repository.CustomerOrderRepository;
import com.kull.repository.CustomerRepository;
import com.kull.repository.OrderLineRepository;
import com.kull.repository.ProductRepository;
import com.kull.service.CustomerOrderService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private WebMapper<CustomerOrderDTO, CustomerOrder> mapper;
    @Autowired
    private WebMapper<CustomerDTO, Customer> mapperCustomer;
    @Autowired
    private CustomerOrderRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Transactional
    @Override
    public void create(CustomerOrderDTO customerOrder) {
        final var orderToCreate = mapper.toEntity(customerOrder);
        repository.save(orderToCreate);
    }


    @Override
    public void createAll(CustomerOrdersDTO customerOrderDtos) {
        customerOrderDtos.getCustomerOrders().forEach(orderDto->{
            final CustomerOrder customerOrder= createOrder(orderDto.getCustomer().getEmail());

            orderDto.getOrderLines().forEach((orderLineDTO -> {
                final ProductDTO productDTO = orderLineDTO.getProduct();

                orderLineRepository.save(OrderLine.builder()
                                .quantity(orderLineDTO.getQuantity())
                                .product(getOrCreateProduct(productDTO.getSkuCode(),
                                        productDTO.getName(),
                                        productDTO.getUnitPrice()))
                                .customerOrder(customerOrder)
                        .build());
            }));
        });
    }
    private static String generateOrderNumber(){
        return UUID.randomUUID().toString().substring(0,19);
    }

    private Customer enrichCustomer(String email){
        Customer customer = customerRepository.findByEmail(email);
        if(customer==null){
            throw  new RuntimeException("Invalid customer");
        }
        return customer;
    }

    private CustomerOrder createOrder(String customerEmail){
        return  repository.save(CustomerOrder.builder()
                        .orderNumber(generateOrderNumber())
                        .customer(enrichCustomer(customerEmail))
                        .submissionDate(ZonedDateTime.now())
                .build());
    }

    private Product getOrCreateProduct(final Integer skuCode, final String productName, final Integer unitPrice) {
    Product product =productRepository.findBySkuCode(skuCode);
    if(product!=null){
        return product;
    }
    product = Product.builder()
            .name(productName)
            .unitPrice(unitPrice)
            .build();

    return productRepository.save(product);
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
