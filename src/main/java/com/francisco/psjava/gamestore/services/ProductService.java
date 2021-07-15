package com.francisco.psjava.gamestore.services;

import com.francisco.psjava.gamestore.dto.ProductDTO;
import com.francisco.psjava.gamestore.exceptions.DataException;
import com.francisco.psjava.gamestore.exceptions.NotFoundException;
import com.francisco.psjava.gamestore.mappers.MapperProduct;
import com.francisco.psjava.gamestore.model.Product;
import com.francisco.psjava.gamestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductDTO> findAll(String attrSort) {
        String orderBy = ProductDTO.productSort(attrSort);
        return MapperProduct.toListDTO(repository.findAll(Sort.by(Sort.Direction.ASC, orderBy)));
    }

    public ProductDTO findById(Long id) {
        Product prod = repository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return MapperProduct.toDTO(repository.save(prod));
    }

    public ProductDTO include(ProductDTO dto) {
        Product newProd = MapperProduct.toEntity(dto);
        return MapperProduct.toDTO(repository.save(newProd));
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataException(e.getMessage());
        }
    }

    public ProductDTO update(Long id, ProductDTO newProd) {
        try {
            Product entity = repository.getById(id);
            MapperProduct.merge(entity, newProd);

            return MapperProduct.toDTO(repository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(id);
        }
    }
}
