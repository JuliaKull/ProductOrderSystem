package com.kull.repository;

import com.kull.model.OrderLine;
import com.kull.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {

    OrderLine findByCustomerOrder(Integer customerOrder);

    List<OrderLine> findAllByProduct(Product product);
}
