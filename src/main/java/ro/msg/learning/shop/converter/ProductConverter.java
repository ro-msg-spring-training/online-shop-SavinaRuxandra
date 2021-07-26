package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;

@Component
public class ProductConverter implements Converter<Product, ProductDto> {
    @Override
    public Product dtoToModel(ProductDto dto) {
        Supplier supplier = Supplier.builder()
                .name(dto.getSupplierName())
                .build();
        supplier.setId(dto.getSupplierId());

        ProductCategory productCategory = ProductCategory.builder()
                .name(dto.getProductCategoryName())
                .description(dto.getProductCategoryDescription())
                .build();
        productCategory.setId(dto.getProductCategoryId());

        Product product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .weight(dto.getWeight())
                .category(productCategory)
                .supplier(supplier)
                .imageUrl(dto.getImageUrl())
                .build();
        product.setId(dto.getId());
        return product;
    }

    @Override
    public ProductDto modelToDto(Product product) {
        ProductDto productDto = ProductDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .weight(product.getWeight())
                .productCategoryId(product.getCategory().getId())
                .productCategoryName(product.getCategory().getName())
                .productCategoryDescription(product.getCategory().getDescription())
                .supplierId(product.getSupplier().getId())
                .supplierName(product.getSupplier().getName())
                .imageUrl(product.getImageUrl())
                .build();
        productDto.setId(product.getId());
        return productDto;
    }
}
