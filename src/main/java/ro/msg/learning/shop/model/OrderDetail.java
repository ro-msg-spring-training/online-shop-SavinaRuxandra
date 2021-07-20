package ro.msg.learning.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.util.Pair;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class OrderDetail extends BaseEntity<Pair<Order, Product>> {

    private Integer quantity;

    public OrderDetail(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderDetail() {
    }
}
