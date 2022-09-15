package com.kull.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Entity
@Table(name = "customer_order")
public class CustomerOrder extends AbstractBaseEntity{

    @Size(max = 20)
    @Column(name = "order_number")
    private String orderNumber;

    @ToString.Exclude
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "submission_date")
    private ZonedDateTime submissionDate;
}
