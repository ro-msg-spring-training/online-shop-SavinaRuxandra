package ro.msg.learning.shop.service.strategyFindLocation;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

@Service
public interface LocationStrategy {

    List<Stock> findBestLocation(List<OrderDetail> orderDetails);
}
