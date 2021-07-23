package ro.msg.learning.shop.service.strategyFindLocation;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.service.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SingleLocationStrategy implements LocationStrategy {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Stock> findBestLocation(List<OrderDetail> orderDetails) {
        List<Stock> stocks = new ArrayList<>();

        Location location = findLocation(orderDetails);
        if(location == null)
            throw new ServiceException("No location is good enough");
        else
            orderDetails.forEach(orderDetail -> {
                        Stock stock = Stock.builder()
                                .product(orderDetail.getProduct())
                                .location(location)
                                .quantity(orderDetail.getQuantity())
                                .build();
                        stocks.add(stock);
                    });
        return stocks;
    }

    private Location findLocation(List<OrderDetail> orderDetails) {
        List<Location> foundLocations = new ArrayList<>();
        locationRepository.findAll().forEach(
                location -> {
                    long productCounter = location.getStocks().stream().filter(stock -> {
                        Integer productId = stock.getProduct().getId();
                        Integer quantityNeeded = orderDetails.stream()
                                .filter(orderDetail -> orderDetail.getProduct().getId().equals(productId))
                                .collect(Collectors.toList())
                                .get(0)
                                .getQuantity();
                        return quantityNeeded != null && stock.getQuantity() >= quantityNeeded;
                    }).count();

                    if(productCounter == orderDetails.size()) {
                        foundLocations.add(location);
                    }
                }
        );
        return foundLocations.size() == 0 ? null : foundLocations.get(0);
    }
}
