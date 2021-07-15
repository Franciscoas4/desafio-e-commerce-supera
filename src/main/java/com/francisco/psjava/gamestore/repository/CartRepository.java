package com.francisco.psjava.gamestore.repository;

import com.francisco.psjava.gamestore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
