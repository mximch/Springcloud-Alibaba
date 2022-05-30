package com.mximch.dao;

import com.mximch.domain.Order;
import com.mximch.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Integer> {
}
