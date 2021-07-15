package com.francisco.psjava.gamestore.dto;

import javax.validation.constraints.NotNull;

public class QuantityProductDTO {

    @NotNull(message = "The id of the product is required")
    private Long id;

    @NotNull(message = "The quantity of product(s) is required")
    private Integer quantity;

    public QuantityProductDTO(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
