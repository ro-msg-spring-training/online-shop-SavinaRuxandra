package ro.msg.learning.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

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

    public Order(Location shippedFrom, Customer customer, LocalDateTime createdAt, Address address) {
        this.shippedFrom = shippedFrom;
        this.customer = customer;
        this.createdAt = createdAt;
        this.address = address;
    }

    public Order() {
    }
}
