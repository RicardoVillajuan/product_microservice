package com.bank.servicedb;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bank.model.Product;
import com.bank.repository.ProductRepository;
import com.bank.service.IProductService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ProductServiceDB implements IProductService{
	
	private final ProductRepository repoProduct;
	
	@Override
	public Flux<Product> findAll() {
		// TODO Auto-generated method stub
		return repoProduct.findAll();
	}

	@Override
	public Mono<Product> save(Product typeproduct) {
		// TODO Auto-generated method stub
		return repoProduct.save(typeproduct);
	}

	@Override
	public Mono<Product> findProductById(String id) {
		// TODO Auto-generated method stub
		return repoProduct.findById(id);
	}

	@Override
	public Mono<Product> update(String id,Product product) {
		// TODO Auto-generated method stub
		return repoProduct.findById(id).flatMap(e->{
			product.setId(e.getId());
			return repoProduct.save(product);
		});
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		repoProduct.deleteById(id).subscribe();
	}

}
