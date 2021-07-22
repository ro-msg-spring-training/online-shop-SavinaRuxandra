package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.model.Product;

import java.util.List;

public interface ProductService {

    void createProduct(ProductDto productDto);

    void updateProduct(Integer id, ProductDto productDto);

    void deleteProduct(Integer id);

    Product readByIdProduct(Integer id);

    List<ProductDto> readAllProduct();
}
