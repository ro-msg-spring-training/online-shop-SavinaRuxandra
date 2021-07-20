package ro.msg.learning.shop.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class BaseEntity<ID> implements Serializable {

    @Id
    private ID id;
}
