package com.francisco.psjava.gamestore.mappers;

import com.francisco.psjava.gamestore.dto.ProductDTO;
import com.francisco.psjava.gamestore.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MapperProduct {

    public static ProductDTO toDTO(Product product) {
        ProductDTO proddto = new ProductDTO();
        proddto.setId(product.getId());
        proddto.setName(product.getName());
        proddto.setScore(product.getScore());
        proddto.setPrice(product.getPrice());
        proddto.setImage(proddto.getImage());

        return proddto;
    }

    public static List<ProductDTO> toListDTO(List<Product> products) {
        List<ProductDTO> prodListDto = new ArrayList<>();
        for (Product prod : products) {
            prodListDto.add(toDTO(prod));
        }
        return prodListDto;
    }

    public static Product toEntity(ProductDTO proddto) {
        Product product = new Product();
        product.setId(proddto.getId());
        product.setName(proddto.getName());
        product.setScore(proddto.getScore());
        product.setPrice(proddto.getPrice());
        product.setImage(proddto.getImage());

        return product;
    }

    public static void merge(Product entity, ProductDTO dto) {
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setScore(dto.getScore());
        entity.setImage(dto.getImage());
    }
}
