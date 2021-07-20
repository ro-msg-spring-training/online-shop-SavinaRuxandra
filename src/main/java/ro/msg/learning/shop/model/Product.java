package ro.msg.learning.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Product extends BaseEntity<Integer> {

    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    @ManyToOne
    @JoinColumn(name="category")
    private ProductCategory category;
    @ManyToOne
    @JoinColumn(name="supplier")
    private Supplier supplier;
    private String imageUrl;

    public Product(String name, String description, BigDecimal price, Double weight, ProductCategory category, Supplier supplier, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.category = category;
        this.supplier = supplier;
        this.imageUrl = imageUrl;
    }

    public Product() {
    }
}
