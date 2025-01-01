package sefyan.customerservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConfigTestRestController {
    @Value("${customer.params.x}") private String x;
    @Value("${customer.params.y}") private String y;
    @Autowired private ConfigClass configClass;

    @GetMapping("/test")
    public Map<String, String> test(){
        return Map.of("x", x, "y", y);
    }

    @GetMapping("/testConfigClass")
    public ConfigClass testConfigClass(){
        return configClass;
    }
}
