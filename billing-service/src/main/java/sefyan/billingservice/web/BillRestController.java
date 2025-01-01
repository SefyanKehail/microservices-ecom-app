package sefyan.billingservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sefyan.billingservice.entities.Bill;
import sefyan.billingservice.feign.CustomerRestClient;
import sefyan.billingservice.feign.ProductRestClient;
import sefyan.billingservice.repositories.BillRepository;
import sefyan.billingservice.repositories.ProductItemRepository;

import java.util.List;

@RestController
public class BillRestController {
    private BillRepository billRepository;
    private CustomerRestClient customerRestClient;
    private ProductRestClient productRestClient;
    private ProductItemRepository productItemRepository;

    public BillRestController(
            BillRepository billRepository, CustomerRestClient customerRestClient,
            ProductItemRepository productItemRepository, ProductRestClient productRestClient
    ) {
        this.billRepository = billRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRepository = productItemRepository;
        this.productRestClient = productRestClient;
    }

    @GetMapping("/bills/{id}")
    public Bill getBill(@PathVariable Long id) {

        System.out.println("entered getBill()");
        Bill bill = billRepository.findById(id).orElseThrow(() -> new RuntimeException("bill not found"));
//        Bill bill = billRepository.findAll().stream().filter(b -> id == b.getId()).toList().getFirst();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));

        bill.getProductItems().forEach(
                productItem -> {
                    productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
                }
        );
        return bill;
    }
}
