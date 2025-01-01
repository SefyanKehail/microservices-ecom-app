package sefyan.billingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sefyan.billingservice.entities.Bill;

@RepositoryRestResource
public interface BillRepository extends JpaRepository<Bill, Long> {
}
