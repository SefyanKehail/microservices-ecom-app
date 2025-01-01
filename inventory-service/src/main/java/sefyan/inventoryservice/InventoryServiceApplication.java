package sefyan.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sefyan.inventoryservice.entites.Product;
import sefyan.inventoryservice.repositories.ProductRepository;

import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner inventoryServiceCLR(ProductRepository productRepository) {
        Random random = new Random();
        return args -> {
            Stream.of("Computer", "Smartphone", "Smartwatch").forEach(
                    name -> {
                        productRepository.save(
                                Product.builder()
                                        .name(name)
                                        .price(random.nextDouble() * 1000)
                                        .quantity(random.nextInt(0, 1000))
                                        .build()
                        );
                    }
            );
        };
    }
}
