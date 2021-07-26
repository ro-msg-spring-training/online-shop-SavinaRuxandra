package ro.msg.learning.shop.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto extends BaseDto {

    private String firstName;
    private String lastName;
    private String username;
    private String emailAddress;
}
