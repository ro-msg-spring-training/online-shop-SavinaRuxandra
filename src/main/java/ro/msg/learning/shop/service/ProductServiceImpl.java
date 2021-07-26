package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.converter.ProductConverter;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.service.exception.InvalidProductIdException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    @Override
    public void createProduct(ProductDto productDto) {
        Product product = productConverter.dtoToModel(productDto);
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Integer id, ProductDto productDto) {
        Product product = productConverter.dtoToModel(productDto);
        productRepository.findById(id).orElseThrow(() -> new InvalidProductIdException("There is no product with this id"));
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.findById(id).orElseThrow(() -> new InvalidProductIdException("There is no product with this id"));
        productRepository.deleteById(id);
    }

    @Override
    public Product readByIdProduct(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new InvalidProductIdException("There is no product with this id"));
    }

    @Override
    public List<ProductDto> readAllProduct() {
        return productRepository.findAll().stream().map(productConverter::modelToDto).collect(Collectors.toList());
    }
}
