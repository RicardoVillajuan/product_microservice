package com.bank.service;

import com.bank.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

	Flux<Product> findAll();
	
	Mono<Product> save(Product product);
	
	Mono<Product> findProductById(String id);
	
	Mono<Product> update(Product product);
	
	void deleteById(String id);
}
