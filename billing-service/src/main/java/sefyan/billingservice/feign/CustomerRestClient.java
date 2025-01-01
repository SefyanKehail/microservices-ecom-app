package sefyan.billingservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sefyan.billingservice.models.Customer;


@FeignClient(name = "customer-service")
public interface CustomerRestClient {
    @GetMapping("/api/customers/{id}")
    Customer getCustomerById(@PathVariable Long id);

    // page model is a wrapper object that is compatible with hateoas design ( similar to rest repos )
    @GetMapping("/api/customers")
    PagedModel<Customer> getAllCustomers();
}
