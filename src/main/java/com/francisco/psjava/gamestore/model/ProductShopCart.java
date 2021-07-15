package com.francisco.psjava.gamestore.model;

import com.francisco.psjava.gamestore.model.PK.CartProductPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "cartProduct_tb")
public class ProductShopCart {

    @EmbeddedId
    private CartProductPK id = new CartProductPK();

    private Integer quantity;

    public ProductShopCart() {}

    public ProductShopCart(Cart cart, Product product, Integer quantity) {
        id.setCart(cart);
        id.setProduct(product);
        this.quantity = quantity;
    }

    public Cart setOrder() {
        return id.getCart();
    }

    public void setOrder(Cart cart) {
        id.setCart(cart);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void quantityAdd(Integer quantity) {
        this.quantity += quantity;
    }

    public void quantityRemove(Integer quantity) {
        this.quantity -= quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductShopCart that = (ProductShopCart) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
