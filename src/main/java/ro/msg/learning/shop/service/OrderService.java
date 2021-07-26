package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.converter.OrderConverter;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.exception.InvalidProductIdException;
import ro.msg.learning.shop.service.strategyFindLocation.LocationStrategy;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final StockRepository stockRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final LocationStrategy locationStrategy;
    private final OrderConverter orderConverter;

    public Order createOrder(OrderDto orderDto) {
        Order order = orderConverter.dtoToModel(orderDto);

        for(Integer product : orderDto.getProducts().keySet())
            productRepository.findById(product).orElseThrow(() -> new InvalidProductIdException("There is no product with this id"));

        List<OrderDetail> orderDetails = orderDto.getProducts().keySet().stream().map(productId -> {
            Product product = productRepository.findById(productId).get();
            return OrderDetail.builder()
                    .order(order)
                    .product(product)
                    .quantity(orderDto.getProducts().get(productId))
                    .build();
        }).collect(Collectors.toList());

        List<Stock> orderStocks = locationStrategy.findBestLocation(orderDetails);
        orderStocks.forEach(orderStock -> {
            Stock stock = stockRepository.findAllByProductAndLocation(orderStock.getProduct(), orderStock.getLocation()).get(0);
            stock.setQuantity(stock.getQuantity() - orderStock.getQuantity());
            stockRepository.save(stock);
        });

        orderRepository.save(order);
        orderDetails.forEach(orderDetailRepository::save);
        return order;
    }

}
