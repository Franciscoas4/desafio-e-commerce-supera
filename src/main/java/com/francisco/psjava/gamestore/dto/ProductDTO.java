package com.francisco.psjava.gamestore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.francisco.psjava.gamestore.enums.CartSortAttr;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private Long id;

    @NotNull(message = "Field name is required")
    private String name;

    @NotNull(message = "Field price is required")
    private BigDecimal price;

    @NotNull(message = "Field score is required")
    private short score;

    @NotNull(message = "An image link is required")
    private String image;
    private Integer quantity;

    public ProductDTO() {}

    public ProductDTO(Long id, String name,BigDecimal price, short score, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.score = score;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public short getScore() {
        return score;
    }

    public void setScore(short score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal totalCalculate() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public static String productSort(String sortAttr) {
        CartSortAttr attr = CartSortAttr.stringValue(sortAttr);

        if (Objects.isNull(attr)) {
            return "id";
        } else {
            return attr.getAttrSort().toLowerCase();
        }
    }

}
