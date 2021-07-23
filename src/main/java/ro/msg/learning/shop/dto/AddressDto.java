package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Address;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String country;
    private String city;
    private String county;
    private String streetAddress;

    public static Address DtoToModel(AddressDto addressDto) {
        return Address.builder()
                .country(addressDto.country)
                .city(addressDto.city)
                .county(addressDto.county)
                .streetAddress(addressDto.streetAddress)
                .build();
    }
}
