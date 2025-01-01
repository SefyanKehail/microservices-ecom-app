package sefyan.billingservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import sefyan.billingservice.entities.Bill;
import sefyan.billingservice.entities.ProductItem;
import sefyan.billingservice.feign.CustomerRestClient;
import sefyan.billingservice.feign.ProductRestClient;
import sefyan.billingservice.models.Customer;
import sefyan.billingservice.models.Product;
import sefyan.billingservice.repositories.BillRepository;
import sefyan.billingservice.repositories.ProductItemRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner billingServiceCLR(
            BillRepository billRepository,
            ProductItemRepository productItemRepository,
            CustomerRestClient customerRestClient,
            ProductRestClient productRestClient
    ) {
        return args -> {
            Random random = new Random();
            Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
            Collection<Product> products = productRestClient.getAllProducts().getContent();


            customers.forEach(
                    customer -> {
                        Bill bill = Bill.builder()
                                .date(LocalDateTime.now())
                                .customer(customer)
                                .customerId(customer.getId())
                                .build();

                        billRepository.save(bill);

                        products.forEach(
                                product -> {
                                    ProductItem productItem = ProductItem.builder()
                                            .product(product)
                                            .productId(product.getId())
                                            .bill(bill)
                                            .quantity(random.nextInt(1, 10))
                                            .unitPrice(product.getPrice() * 0.95)
                                            .build();

                                    productItemRepository.save(productItem);
                                }
                        );


                    }
            );
        };
    }
}
