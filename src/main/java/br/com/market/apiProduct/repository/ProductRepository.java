package br.com.market.apiProduct.repository;

import br.com.market.apiProduct.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product, Integer> {
    Integer id(int id);
}
