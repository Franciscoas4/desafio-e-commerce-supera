package com.francisco.psjava.gamestore.repository;

import com.francisco.psjava.gamestore.model.ProductShopCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductShopCartRepository extends JpaRepository<ProductShopCart, Long> {
}
