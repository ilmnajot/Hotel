package uz.ilmnajot.hotel_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class WebConfig {

    @Bean
    public HiddenHttpMethodFilter filter(){
        return new HiddenHttpMethodFilter();
    }
}
