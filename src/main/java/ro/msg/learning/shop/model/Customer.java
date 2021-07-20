package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity

public class Customer extends BaseEntity<Integer> {

    private String lastName;
    private String firstName;
    private String username;
    private String password;
    private String emailAddress;

}
