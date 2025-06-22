package br.com.market.apiProduct.service;

import br.com.market.apiProduct.model.Product;
import br.com.market.apiProduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

@Autowired
private ProductRepository productRepository;

public Product postProduct(Product product) {
    return productRepository.save(product);
}

public List<Product> getProduct() {
    return productRepository.findAll();
}


public Product getProductById(int id) {
   Optional<Product> product = productRepository.findById(id);
   if (product.isPresent()) {
       return product.get();
   }throw new ResponseStatusException(HttpStatus.NOT_FOUND,"product "+id+" not found");
}

public Product updateProduct(int id,Product product) {
    var product1 = productRepository.findById(id).orElse(null);
    if (product1 != null) {
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setQuantity(product.getQuantity());
        return productRepository.save(product1);
    }throw new ResponseStatusException(HttpStatus.NOT_FOUND,"product "+id+" not found");
}

public void deleteProduct(int id) {
    var product1 = productRepository.findById(id).orElse(null);
    if (product1 != null) {
        productRepository.deleteById(product1.getId());
    }else{
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"product "+id+" not found");
    }
}


}