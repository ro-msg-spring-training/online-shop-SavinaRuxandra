package ro.msg.learning.shop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.service.strategyFindLocation.SingleLocationStrategy;
import ro.msg.learning.shop.service.strategyFindLocation.LocationStrategy;

@Configuration
public class ClassConfig {

    @Bean
    public LocationStrategy strategy() {
        return new SingleLocationStrategy();
    }
}
