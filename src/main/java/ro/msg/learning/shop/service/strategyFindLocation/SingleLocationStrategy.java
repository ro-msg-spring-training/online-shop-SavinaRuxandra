package ro.msg.learning.shop.service.strategyFindLocation;

import lombok.AllArgsConstructor;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.service.exception.NoLocationFoundError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SingleLocationStrategy implements LocationStrategy {

    private final LocationRepository locationRepository;

    @Override
    public List<Stock> findBestLocation(List<OrderDetail> orderDetails) {

        Optional<Location> location = findLocation(orderDetails);
        if(location.isEmpty())
            throw new NoLocationFoundError("No location is good enough");
        else
            return orderDetails.stream()
                    .map(orderDetail ->
                        Stock.builder()
                                .product(orderDetail.getProduct())
                                .location(location.get())
                                .quantity(orderDetail.getQuantity())
                                .build()
                    ).collect(Collectors.toList());
    }

    protected Optional<Location> findLocation(List<OrderDetail> orderDetails) {
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
        return foundLocations.size() == 0 ? Optional.empty() : Optional.of(foundLocations.get(0));
    }
}
