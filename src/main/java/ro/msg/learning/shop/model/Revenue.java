package ro.msg.learning.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Revenue extends BaseEntity<Integer> {

    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;
    private LocalDate date;
    private BigDecimal sum;

    public Revenue(Location location, LocalDate date, BigDecimal sum) {
        this.location = location;
        this.date = date;
        this.sum = sum;
    }

    public Revenue() {
    }
}
