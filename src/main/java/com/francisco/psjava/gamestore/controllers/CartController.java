package com.francisco.psjava.gamestore.controllers;

import com.francisco.psjava.gamestore.dto.CartDTO;
import com.francisco.psjava.gamestore.dto.QuantityProductDTO;
import com.francisco.psjava.gamestore.services.CartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/carts")
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping
    public ResponseEntity<List<CartDTO>> findAll(@RequestParam(required = false) String attrSort) {
        List<CartDTO> list = service.findAll(StringUtils.upperCase(attrSort));
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CartDTO> findById(@PathVariable Long id, @RequestParam(required = false) String sortAttribute) {
        CartDTO obj = service.findById(id, StringUtils.upperCase(sortAttribute));
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/checkout/{id}")
    public ResponseEntity<CartDTO> checkout(@PathVariable Long id) {
        CartDTO obj = service.checkout(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<CartDTO> insert(@RequestBody List<@Valid QuantityProductDTO> products) {
        CartDTO newShopcart = service.include(products);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newShopcart.getId()).toUri();
        return ResponseEntity.created(uri).body(newShopcart);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "product-add/{id}")
    public ResponseEntity<CartDTO> addProduct(@PathVariable Long id, @RequestBody List<@Valid QuantityProductDTO> products) {
        CartDTO editedShopcart = service.productAdd(id, products);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(products).toUri();
        return ResponseEntity.created(uri).body(editedShopcart);
    }

    @DeleteMapping(value = "product-remove/{id}")
    public ResponseEntity<CartDTO> removeProduct(@PathVariable Long id, @RequestBody List<@Valid QuantityProductDTO> products) {
        CartDTO editedShopcart = service.productRemove(id, products);
        return ResponseEntity.ok().body(editedShopcart);
    }
}
