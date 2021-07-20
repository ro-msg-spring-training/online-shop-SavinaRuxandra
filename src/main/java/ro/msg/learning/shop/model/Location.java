package ro.msg.learning.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Location extends BaseEntity<Integer> {

    private String name;
    private Address address;

    public Location(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Location() {
    }
}
