package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Stock extends BaseEntity<Integer> {

    @ManyToOne
    @JoinColumn(name="product")
    private Product product;
    @ManyToOne
    @JoinColumn(name="location")
    private Location location;
    private Integer quantity;

}
