package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
public class Location extends BaseEntity<Integer> {

    private String name;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "location")
    private List<Stock> stocks;
}
