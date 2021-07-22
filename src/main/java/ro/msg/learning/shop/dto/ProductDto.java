package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private Integer productCategoryId;
    private String productCategoryName;
    private String productCategoryDescription;
    private Integer supplierId;
    private String supplierName;
    private String imageUrl;

    public static ProductDto convertModelToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
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
    }

    public static Product convertDtoToModel(ProductDto productDto) {
        Supplier supplier = Supplier.builder()
                .name(productDto.getSupplierName())
                .build();
        supplier.setId(productDto.getSupplierId());

        ProductCategory productCategory = ProductCategory.builder()
                .name(productDto.getProductCategoryName())
                .description(productDto.getProductCategoryDescription())
                .build();
        productCategory.setId(productDto.getProductCategoryId());

        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .weight(productDto.getWeight())
                .category(productCategory)
                .supplier(supplier)
                .imageUrl(productDto.getImageUrl())
                .build();
        product.setId(productDto.getId());
        return product;
    }
}
