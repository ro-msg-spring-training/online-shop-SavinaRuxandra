package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Order;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private LocalDateTime createdAt;
    private AddressDto address;
    private Map<Integer, Integer> products;

    public static Order DtoToModel(OrderDto orderdto) {
        return Order.builder()
                .createdAt(orderdto.createdAt)
                .address(AddressDto.DtoToModel(orderdto.address))
                .build();
    }
}
