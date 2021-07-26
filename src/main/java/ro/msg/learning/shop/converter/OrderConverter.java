package ro.msg.learning.shop.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.Address;
import ro.msg.learning.shop.model.Order;

@Component
@AllArgsConstructor
public class OrderConverter implements Converter<Order, OrderDto> {

    private final CustomerConverter customerConverter;

    @Override
    public Order dtoToModel(OrderDto dto) {
        Address address = Address.builder()
                .country(dto.getAddress_country())
                .city(dto.getAddress_city())
                .county(dto.getAddress_county())
                .streetAddress(dto.getAddress_streetAddress())
                .build();

        return Order.builder()
                .customer(customerConverter.dtoToModel(dto.getCustomerDto()))
                .createdAt(dto.getCreatedAt())
                .address(address)
                .build();
    }

    @Override
    public OrderDto modelToDto(Order order) {
        return OrderDto.builder()
                .customerDto(customerConverter.modelToDto(order.getCustomer()))
                .createdAt(order.getCreatedAt())
                .address_country(order.getAddress().getCountry())
                .address_city(order.getAddress().getCity())
                .address_county(order.getAddress().getCity())
                .address_streetAddress(order.getAddress().getStreetAddress())
                .build();
    }
}
