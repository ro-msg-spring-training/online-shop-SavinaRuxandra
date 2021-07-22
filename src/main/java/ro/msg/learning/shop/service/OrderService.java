package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.strategyFindLocation.LocationStrategy;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LocationStrategy locationStrategy;

    public OrderService() {
    }

    public void setStrategy(LocationStrategy locationStrategy) {
        this.locationStrategy = locationStrategy;
    }

    public Order createOrder(OrderDto orderDto) {
        Order order = OrderDto.DtoToModel(orderDto);

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDto.getProducts().forEach((productId, quantity) -> {
            Product product = productRepository.findById(productId).orElseThrow(() -> new ServiceException("Invalid product id"));
            OrderDetail orderDetail = OrderDetail.builder()
                    .order(order)
                    .product(product)
                    .quantity(quantity)
                    .build();
            orderDetails.add(orderDetail);
        });

        List<Stock> orderStocks = locationStrategy.findBestLocation(orderDetails);
        orderStocks.forEach(orderStock -> {
            Stock stock = stockRepository.findAllByProductAndLocation(orderStock.getProduct(), orderStock.getLocation()).get(0);
            stock.setQuantity(stock.getQuantity() - orderStock.getQuantity());
        });

        orderRepository.save(order);
        orderDetails.forEach(orderDetail -> orderDetailRepository.save(orderDetail));
        return order;
    }

}
