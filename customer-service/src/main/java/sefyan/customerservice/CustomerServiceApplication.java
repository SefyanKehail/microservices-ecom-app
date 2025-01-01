package sefyan.customerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import sefyan.customerservice.config.ConfigClass;
import sefyan.customerservice.entities.Customer;
import sefyan.customerservice.repositories.CustomerRepository;

import java.util.stream.Stream;

@SpringBootApplication
@EnableConfigurationProperties(ConfigClass.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner customerServiceCLR(CustomerRepository customerRepository){
        return args -> {
            Stream.of("Farid","Amina","Karim").forEach(
                    name -> {
                        customerRepository.save(
                                Customer.builder()
                                        .name(name)
                                        .email(name + "@gmail.com")
                                        .build()
                        );
                    }
            );
        };
    }
}
