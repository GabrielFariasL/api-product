package br.com.market.apiProduct.controller;

import br.com.market.apiProduct.model.Product;
import br.com.market.apiProduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")

public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity productPost(@RequestBody Product product) {
        productService.postProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity productGet() {
        return ResponseEntity.ok(productService.getProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity productGetById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity productPut(@PathVariable int id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity productDelete(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
