package com.kull.model;


import jdk.jfr.Name;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer extends AbstractBaseEntity{

    @Size(max = 20)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 20)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "registration_code")
    private BigInteger registrationCode;

    @Size(max = 50)
    private String email;

    @Size(max = 50)
    private String telephone;


    @ToString.Exclude
    @OneToMany(mappedBy = "customer",
            orphanRemoval = true)
    @Cascade(SAVE_UPDATE)
    private List<CustomerOrder> customerOrders;
}
