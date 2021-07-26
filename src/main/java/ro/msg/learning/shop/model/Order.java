package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table(name = "orders")
public class Order extends BaseEntity<Integer> {

    @ManyToOne
    @JoinColumn(name = "shipped_from")
    private Location shippedFrom;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Embedded
    private Address address;

}
