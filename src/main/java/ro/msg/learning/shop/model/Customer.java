package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
public class Customer extends BaseEntity<Integer> {

    private String lastName;
    private String firstName;
    private String username;
    private String password;
    private String emailAddress;

}
