package ro.msg.learning.shop.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {

    private String country;
    private String city;
    private String county;
    private String streetAddress;

    public Address(String country, String city, String county, String streetAddress) {
        this.country = country;
        this.city = city;
        this.county = county;
        this.streetAddress = streetAddress;
    }

    public Address() {
    }
}
