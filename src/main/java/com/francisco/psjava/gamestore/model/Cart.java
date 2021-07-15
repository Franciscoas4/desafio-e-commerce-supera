package com.francisco.psjava.gamestore.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cart_tb")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime instant;

    @OneToMany(mappedBy = "id.cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductShopCart> products = new ArrayList<>();

    public Cart() {}

    public Cart(Long id, LocalDateTime instant) {
        this.id = id;
        this.instant = instant;
    }

    public Integer findIndexProduct(Long id) {
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getProduct().getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getInstant() {
        return instant;
    }

    public void setInstant(LocalDateTime instant) {
        this.instant = instant;
    }

    public List<ProductShopCart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductShopCart> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id.equals(cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
