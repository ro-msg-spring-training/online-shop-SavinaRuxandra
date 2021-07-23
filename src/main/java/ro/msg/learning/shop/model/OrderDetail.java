package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table(name = "order_detail")
public class OrderDetail extends BaseEntity<Integer> {

    @ManyToOne
    @JoinColumn(name = "orders")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;
    private Integer quantity;

}
