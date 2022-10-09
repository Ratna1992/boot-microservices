package com.ratna.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ratna.os.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
