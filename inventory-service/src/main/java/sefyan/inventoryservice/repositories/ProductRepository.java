package sefyan.inventoryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sefyan.inventoryservice.entites.Product;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, String> {
}
