package ro.msg.learning.shop.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends BaseDto {

    private LocalDateTime createdAt;
    private String address_country;
    private String address_city;
    private String address_county;
    private String address_streetAddress;
    private Map<Integer, Integer> products;

}
