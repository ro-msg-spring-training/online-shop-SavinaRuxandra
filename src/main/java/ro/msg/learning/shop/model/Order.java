package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Order extends BaseEntity<Integer> {

    @ManyToOne
    @JoinColumn(name = "shippedForm")
    private Location shippedFrom;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    private LocalDateTime createdAt;
    private Address address;

}
