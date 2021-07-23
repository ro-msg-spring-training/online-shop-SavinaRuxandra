package ro.msg.learning.shop.service.strategyFindLocation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;

import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class MostAbundantLocationStrategyTest {

    @Mock
    private StockRepository stockRepository;
    @InjectMocks
    private LocationStrategy mostAbundantLocationStrategy = new MostAbundantLocationStrategy();

    private Product PRODUCT1 = Product.builder().build();
    private Product PRODUCT2 = Product.builder().build();

    private Location LOCATION1 = Location.builder().build();
    private Location LOCATION2 = Location.builder().build();

    private Stock STOCK1_1;
    private Stock STOCK2_2;
    private Stock STOCK1_2;

    private Integer QUANTITY1_1 = 11;
    private Integer QUANTITY2_2 = 22;
    private Integer QUANTITY1_2 = 12;

    @BeforeEach
    void setUp() {
        PRODUCT1.setId(1);
        PRODUCT2.setId(2);

        LOCATION1.setId(1);
        LOCATION2.setId(2);

        STOCK1_1 = Stock.builder()
                .product(PRODUCT1)
                .location(LOCATION1)
                .quantity(QUANTITY1_1)
                .build();
        STOCK2_2 = Stock.builder()
                .product(PRODUCT2)
                .location(LOCATION2)
                .quantity(QUANTITY2_2)
                .build();
        STOCK1_2 = Stock.builder()
                .product(PRODUCT1)
                .location(LOCATION2)
                .quantity(QUANTITY1_2)
                .build();

        LOCATION1.setStocks(List.of(STOCK1_1));
        LOCATION2.setStocks(List.of(STOCK2_2, STOCK1_2));
    }

    @Test
    void findBestMostAbundantLocation() {
        Integer orderQuantity = 5;

        OrderDetail orderDetail1 = OrderDetail.builder()
                .product(PRODUCT1)
                .quantity(orderQuantity)
                .build();
        OrderDetail orderDetail2 = OrderDetail.builder()
                .product(PRODUCT2)
                .quantity(orderQuantity)
                .build();

        when(stockRepository.findAllByProduct(PRODUCT1)).thenReturn(List.of(STOCK1_1, STOCK1_2));
        when(stockRepository.findAllByProduct(PRODUCT2)).thenReturn(List.of(STOCK2_2));

        Stock stockExpected1 = Stock.builder()
                .product(PRODUCT1)
                .location(LOCATION2)
                .quantity(orderQuantity)
                .build();
        Stock stockExpected2 = Stock.builder()
                .product(PRODUCT2)
                .location(LOCATION2)
                .quantity(orderQuantity)
                .build();
        List<Stock> stocksExpected = List.of(stockExpected1, stockExpected2);

        assertEquals(mostAbundantLocationStrategy.findBestLocation(List.of(orderDetail1, orderDetail2)), stocksExpected);
    }
}