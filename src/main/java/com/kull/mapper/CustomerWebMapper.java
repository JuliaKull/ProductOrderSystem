package com.kull.mapper;

import com.kull.dto.CustomerDTO;
import com.kull.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerWebMapper implements WebMapper<CustomerDTO, Customer> {
    @Override
    public CustomerDTO toDTO(Customer entity) {
        if(entity==null){
            return null;
        }
        return CustomerDTO.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .telephone(entity.getTelephone())
                .registrationCode(entity.getRegistrationCode())
                .build();
    }

    @Override
    public Customer toEntity(CustomerDTO dto) {
        if(dto==null){
            return null;
        }
        return Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .telephone(dto.getTelephone())
                .registrationCode(dto.getRegistrationCode())
                .build();
    }
}
