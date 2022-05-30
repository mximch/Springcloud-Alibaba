package com.mximch.dao;

import com.mximch.domain.Product;
import com.mximch.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Integer> {
}
