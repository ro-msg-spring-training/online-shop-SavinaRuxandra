package ro.msg.learning.shop.service.strategyFindLocation;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.ServiceException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MostAbundantLocationLocationStrategy implements LocationStrategy {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Stock> findBestLocation(List<OrderDetail> orderDetails) {
        List<Stock> stocks = new ArrayList<>();

        orderDetails.forEach(orderDetail -> {
                    Location location = findLocationForProduct(orderDetail.getProduct(), orderDetail.getQuantity());
                    Stock stock = Stock.builder()
                            .product(orderDetail.getProduct())
                            .location(location)
                            .quantity(orderDetail.getQuantity())
                            .build();
                    stocks.add(stock);
        });
        return stocks;
    }

    private Location findLocationForProduct(Product product, Integer quantity) {
        return stockRepository.findAllByProduct(product)
                .stream()
                .filter(stock -> stock.getQuantity() >= quantity)
                .max(Comparator.comparing(Stock::getQuantity))
                .orElseThrow(() -> new ServiceException("No location is good enough"))
                .getLocation();
    }
}
