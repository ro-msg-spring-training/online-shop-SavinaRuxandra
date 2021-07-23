package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.Address;
import ro.msg.learning.shop.model.Order;

@Component
public class OrderConverter implements Converter<Order, OrderDto> {
    @Override
    public Order dtoToModel(OrderDto dto) {
        Address address = Address.builder()
                .country(dto.getAddress_country())
                .city(dto.getAddress_city())
                .county(dto.getAddress_county())
                .streetAddress(dto.getAddress_streetAddress())
                .build();

        return Order.builder()
                .createdAt(dto.getCreatedAt())
                .address(address)
                .build();
    }

    @Override
    public OrderDto modelToDto(Order order) {
        return OrderDto.builder()
                .createdAt(order.getCreatedAt())
                .address_country(order.getAddress().getCountry())
                .address_city(order.getAddress().getCity())
                .address_county(order.getAddress().getCity())
                .address_streetAddress(order.getAddress().getStreetAddress())
                .build();
    }
}
