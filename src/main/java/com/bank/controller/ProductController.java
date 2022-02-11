package com.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bank.model.Product;
import com.bank.repository.ProductRepository;
import com.bank.service.IProductService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

	private final IProductService productService;
	
	@GetMapping
	public ResponseEntity<Flux<Product>>  findAll(){
		
		return ResponseEntity.ok(productService.findAll());
	}
	
	@GetMapping("/{id}")
	public Mono<Product> findById(@PathVariable String id){
		
		return productService.findProductById(id);
	}
	
	@PutMapping("/{id}")
	public Mono<Product> saveProduct(@PathVariable String id ,@RequestBody Product product){
		
		return productService.findProductById(id).flatMap(e->{
			e.setName(product.getName());
			e.setType(product.getType());
			return productService.save(e);
		});
	}
	
	@DeleteMapping("/{id}")
	void deleteProduct(@PathVariable String id){
		
		productService.deleteById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Product> saveTypeProduct(@RequestBody Product product){
		
		return productService.save(product);
	}
	
	
}
