package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.model.Customer;

@Component
public class CustomerConverter implements Converter<Customer, CustomerDto> {
    @Override
    public Customer dtoToModel(CustomerDto dto) {
        Customer customer = Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .username(dto.getUsername())
                .emailAddress(dto.getEmailAddress())
                .build();
        customer.setId(dto.getId());
        return customer;
    }

    @Override
    public CustomerDto modelToDto(Customer customer) {
        CustomerDto customerDto = CustomerDto.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .username(customer.getUsername())
                .emailAddress(customer.getEmailAddress())
                .build();
        customerDto.setId(customerDto.getId());
        return customerDto;
    }

}
