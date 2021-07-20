package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
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

}
