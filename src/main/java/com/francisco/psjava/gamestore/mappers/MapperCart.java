package com.francisco.psjava.gamestore.mappers;

import com.francisco.psjava.gamestore.dto.CartDTO;
import com.francisco.psjava.gamestore.dto.ProductDTO;
import com.francisco.psjava.gamestore.model.Cart;
import com.francisco.psjava.gamestore.model.ProductShopCart;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MapperCart {

    public static CartDTO toDTO(Cart cart, String attrSort) {
        CartDTO dto = new CartDTO();

        dto.setId(cart.getId());
        dto.setInstant(cart.getInstant());

        for (ProductShopCart psc : cart.getProducts()) {
            ProductDTO pdto = MapperProduct.toDTO(psc.getProduct());
            pdto.setQuantity(psc.getQuantity());
            dto.setProductQuantity(dto.getProductQuantity() + psc.getQuantity());
            dto.getProducts().add(pdto);
        }

        if (StringUtils.isNotBlank(attrSort)) {
            dto.productsOrder(attrSort);
        }
        return dto;
    }

    public static CartDTO toDTO(Cart cart) { // overload
        return toDTO(cart, null);
    }

    public static List<CartDTO> toDTOList(List<Cart> carts, String attrSort) {
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (Cart shop : carts) {
            cartDTOList.add(toDTO(shop, attrSort));
        }
        return cartDTOList;
    }

    public static List<CartDTO> toDTOList(List<Cart> carts) { // overload
        return toDTOList(carts, null);
    }
}
