package ro.msg.learning.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.util.Pair;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Stock extends BaseEntity<Pair<Product, Location>> {

    private Integer quantity;

    public Stock(Integer quantity) {
        this.quantity = quantity;
    }

    public Stock() {
    }
}
