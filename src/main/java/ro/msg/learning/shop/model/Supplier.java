package ro.msg.learning.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Supplier extends BaseEntity<Integer> {

    private String name;

    public Supplier(String name) {
        this.name = name;
    }

    public Supplier() {
    }
}
