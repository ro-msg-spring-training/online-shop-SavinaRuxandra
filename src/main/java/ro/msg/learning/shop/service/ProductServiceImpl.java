package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public void createProduct(ProductDto productDto) {
        supplierRepository.findById(productDto.getSupplierId())
                .orElseThrow(() -> new ServiceException("Invalid supplier id"));
        productCategoryRepository.findById(productDto.getProductCategoryId())
                .orElseThrow(() -> new ServiceException("Invalid product category id"));

        Product product = ProductDto.convertDtoToModel(productDto);
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Integer id, ProductDto productDto) {
        supplierRepository.findById(productDto.getSupplierId())
                .orElseThrow(() -> new ServiceException("Invalid supplier id"));
        productCategoryRepository.findById(productDto.getProductCategoryId())
                .orElseThrow(() -> new ServiceException("Invalid product category id"));

        productRepository.findById(id).orElseThrow(() -> new ServiceException("Invalid product id"));
        productRepository.deleteById(id);

        Product product = ProductDto.convertDtoToModel(productDto);
        product.setId(id);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.findById(id).orElseThrow(() -> new ServiceException("Invalid product id"));
        productRepository.deleteById(id);
    }

    @Override
    public Product readByIdProduct(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ServiceException("Invalid product id"));
    }

    @Override
    public List<ProductDto> readAllProduct() {
        return productRepository.findAll().stream().map(ProductDto::convertModelToDto).collect(Collectors.toList());
    }
}
