package ro.msg.learning.shop.service.strategyFindLocation;

import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

public interface LocationStrategy {

    List<Stock> findBestLocation(List<OrderDetail> orderDetails);
}
