package ro.msg.learning.shop.service.strategyFindLocation;

import lombok.AllArgsConstructor;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.exception.NoLocationFoundError;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MostAbundantLocationStrategy implements LocationStrategy {

    private final StockRepository stockRepository;

    @Override
    public List<Stock> findBestLocation(List<OrderDetail> orderDetails) {
        return orderDetails.stream().map(orderDetail -> {
                    Location location = findLocationForProduct(orderDetail);
                    return Stock.builder()
                            .product(orderDetail.getProduct())
                            .location(location)
                            .quantity(orderDetail.getQuantity())
                            .build();
        }).collect(Collectors.toList());
    }

    protected Location findLocationForProduct(OrderDetail orderDetail) {
        return stockRepository.findAllByProduct(orderDetail.getProduct())
                .stream()
                .filter(stock -> stock.getQuantity() >= orderDetail.getQuantity())
                .max(Comparator.comparing(Stock::getQuantity))
                .orElseThrow(() -> new NoLocationFoundError("No location is good enough"))
                .getLocation();
    }
}
