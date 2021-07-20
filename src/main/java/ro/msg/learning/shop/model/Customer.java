package ro.msg.learning.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Customer extends BaseEntity<Integer> {

    private String lastName;
    private String firstName;
    private String username;
    private String password;
    private String emailAddress;

    public Customer(String lastName, String firstName, String username, String password, String emailAddress) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public Customer() {
    }
}
