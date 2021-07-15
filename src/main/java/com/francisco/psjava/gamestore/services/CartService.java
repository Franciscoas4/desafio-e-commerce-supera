package com.francisco.psjava.gamestore.services;

import com.francisco.psjava.gamestore.dto.CartDTO;
import com.francisco.psjava.gamestore.dto.QuantityProductDTO;
import com.francisco.psjava.gamestore.exceptions.DataException;
import com.francisco.psjava.gamestore.exceptions.NotFoundException;
import com.francisco.psjava.gamestore.mappers.MapperCart;
import com.francisco.psjava.gamestore.model.Cart;
import com.francisco.psjava.gamestore.model.ProductShopCart;
import com.francisco.psjava.gamestore.repository.CartRepository;
import com.francisco.psjava.gamestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired()
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CartDTO> findAll(String attrSort) {
        return MapperCart.toDTOList(cartRepository.findAll(), attrSort);
    }

    public CartDTO findById(Long id, String attrSort) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return MapperCart.toDTO(cart, attrSort);
    }

    public CartDTO findById(Long id) {
        return findById(id, null);
    }
    public CartDTO checkout(Long id) {
        CartDTO dto = this.findById(id);
        dto.priceCalculator();
        return dto;
    }

    public CartDTO include(List<QuantityProductDTO> products) {
        Cart cart = new Cart();

        for (QuantityProductDTO product : products) {
            Integer quantity = product.getQuantity().equals(0) ? 1 : product.getQuantity();
            cart.getProducts().add(new ProductShopCart(cart, productRepository.getById(product.getId()), quantity));
        }
        return MapperCart.toDTO(cartRepository.save(cart));
    }

    public void delete(Long id) {

        try {
            cartRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataException(e.getMessage());
        }
    }

    public CartDTO productAdd(Long id, List<QuantityProductDTO> products) {
        Cart entity = cartRepository.getById(id);

        for (QuantityProductDTO product : products) {
            Integer productIndex = entity.findIndexProduct(product.getId());
            Integer quantity = product.getQuantity().equals(0) ? 1 : product.getQuantity();
            if (productIndex == -1) {
                entity.getProducts().add(
                        new ProductShopCart(entity, productRepository.getById(product.getId()), quantity)
                );
            } else {
                entity.getProducts().get(productIndex).quantityAdd(quantity);
            }
        }
        return MapperCart.toDTO(cartRepository.save(entity));
    }

    public CartDTO productRemove(Long id, List<QuantityProductDTO> products) {
        Cart entity = cartRepository.getById(id);

        for (QuantityProductDTO product : products) {
            Integer productIndex = entity.findIndexProduct(product.getId());
            Integer quantity = product.getQuantity().equals(0) ? 1 : product.getQuantity();
            if (productIndex != -1) {
                entity.getProducts().get(productIndex).quantityRemove(quantity);
                if (entity.getProducts().get(productIndex).getQuantity() <= 0) {
                    entity.getProducts().remove(productIndex.intValue());
                }
            } else {
                throw new NotFoundException("Product with id: " + product.getId() + " not found on cart with id: " + id);
            }
        }

        return MapperCart.toDTO(cartRepository.save(entity));

    }
}
