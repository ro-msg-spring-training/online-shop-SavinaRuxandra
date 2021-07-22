package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
public class OrderDetail extends BaseEntity<Integer> {

    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;
    private Integer quantity;

}
