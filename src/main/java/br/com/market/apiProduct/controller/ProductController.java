package br.com.market.apiProduct.controller;

import br.com.market.apiProduct.dto.ProductMapper;
import br.com.market.apiProduct.dto.ProductRequestDTO;
import br.com.market.apiProduct.dto.ProductResponseDTO;
import br.com.market.apiProduct.model.Product;
import br.com.market.apiProduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/product")

public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity productPost(@RequestBody ProductRequestDTO productRequest) {
        Product product = ProductMapper.toProduct(productRequest);
        productService.postProduct(product);
        ProductResponseDTO productResponse = ProductMapper.toResponse(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @GetMapping
    public  ResponseEntity<List<ProductResponseDTO>> productGet() {

        List<ProductResponseDTO> productResponseDTOList = productService.getProduct().stream().map(ProductMapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok().body(productResponseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity productGetById(@PathVariable int id) {
        ResponseEntity<ProductResponseDTO> responseDTO = productService.getProductById(id).map(ProductMapper::toResponse).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity productPut(@PathVariable int id, @RequestBody ProductRequestDTO productRequest) {
        Product product = ProductMapper.toProduct(productRequest);
        productService.updateProduct(id, product);
        ProductResponseDTO productResponse = ProductMapper.toResponse(product);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity productDelete(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
