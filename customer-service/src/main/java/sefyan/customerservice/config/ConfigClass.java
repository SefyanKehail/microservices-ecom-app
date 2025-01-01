package sefyan.customerservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

// Somehow a record didn't work for me, I followed the docs by adding a getter and setter to the class and it worked
// ConfigurationProperties doesn't get refreshed automatically like if you're using @Value
// It works like this 🤷‍♂️
@ConfigurationProperties(prefix = "customer.params")
@Getter
@Setter
public class ConfigClass {
    private String x;
    private String y;
}
