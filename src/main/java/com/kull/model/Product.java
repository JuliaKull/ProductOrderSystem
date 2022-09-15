package com.kull.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Product extends AbstractBaseEntity{

    @Size(max = 100)
    @NotBlank
    private String name;

    @NonNull
    @Column(name = "sku_code")
    private Integer skuCode;

    @NonNull
    @Min(value = 0)
    @Column(name = "unit_price")
    private Integer unitPrice;

    @OneToMany(fetch = EAGER, mappedBy = "product",cascade = ALL)
    private List<OrderLine> orderLines;

}
