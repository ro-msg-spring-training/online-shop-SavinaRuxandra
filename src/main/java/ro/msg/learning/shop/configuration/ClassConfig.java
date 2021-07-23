package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.strategyFindLocation.MostAbundantLocationStrategy;
import ro.msg.learning.shop.service.strategyFindLocation.SingleLocationStrategy;
import ro.msg.learning.shop.service.strategyFindLocation.LocationStrategy;

@Configuration
public class ClassConfig {

    @Value("${locationStrategy.type}")
    private String strategy;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private StockRepository stockRepository;

    @Bean
    public LocationStrategy strategy() {
        if(strategy.equals("singleLocation"))
            return new SingleLocationStrategy(locationRepository);
        else
            return new MostAbundantLocationStrategy(stockRepository);
    }
}
