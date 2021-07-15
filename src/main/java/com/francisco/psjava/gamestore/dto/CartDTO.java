package com.francisco.psjava.gamestore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.francisco.psjava.gamestore.dto.ProductDTO;
import com.francisco.psjava.gamestore.enums.CartSortAttr;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDTO {

    private Long id;
    private LocalDateTime instant;
    private BigDecimal dispatch;
    private BigDecimal total;
    private BigDecimal subTotal;
    private Integer productQuantity = 0;

    private List<ProductDTO> products = new ArrayList<>();

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

    public BigDecimal getDispatch() {
        return dispatch;
    }

    public void setDispatch(BigDecimal dispatch) {
        this.dispatch = dispatch;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public void productsOrder(String attOrder) {
        switch (CartSortAttr.valueOf(attOrder)) {
            case NAME:
                this.products = this.getProducts().stream().sorted(Comparator.comparing(ProductDTO::getName)).collect(Collectors.toList());
                break;
            case PRICE:
                this.products = this.getProducts().stream().sorted(Comparator.comparing(ProductDTO::getPrice)).collect(Collectors.toList());
                break;
            case SCORE:
                this.products = this.getProducts().stream().sorted(Comparator.comparing(ProductDTO::getScore)).collect(Collectors.toList());
        }
    }

    public void priceCalculator() {
        this.dispatch = new BigDecimal("0.0");
        this.total = new BigDecimal("0.0");
        this.subTotal = new BigDecimal("0.0");

        for (ProductDTO product : this.products) {
            this.subTotal = this.subTotal.add(product.totalCalculate());
        }

        if (subTotal.intValue() < 250) {
            this.dispatch = BigDecimal.valueOf(productQuantity * 10);
        }
        this.total = this.dispatch.add(subTotal);
    }
}
