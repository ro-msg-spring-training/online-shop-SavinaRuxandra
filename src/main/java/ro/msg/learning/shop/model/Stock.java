package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
public class Stock extends BaseEntity<Integer> {

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name="product")
    private Product product;
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name="location")
    private Location location;
    private Integer quantity;

}
