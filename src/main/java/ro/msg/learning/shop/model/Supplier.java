package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
public class Supplier extends BaseEntity<Integer> {

    private String name;

}
