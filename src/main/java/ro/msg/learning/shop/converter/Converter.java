package ro.msg.learning.shop.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.BaseDto;
import ro.msg.learning.shop.model.BaseEntity;

@Component
public interface Converter<Model extends BaseEntity<Integer>, Dto extends BaseDto> {

    Model dtoToModel(Dto dto);

    Dto modelToDto(Model model);
}
