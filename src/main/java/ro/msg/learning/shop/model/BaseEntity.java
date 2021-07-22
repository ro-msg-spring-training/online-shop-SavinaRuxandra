package ro.msg.learning.shop.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class BaseEntity<ID> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;
}
