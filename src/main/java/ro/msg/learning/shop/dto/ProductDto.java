package ro.msg.learning.shop.dto;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends BaseDto {

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

}
