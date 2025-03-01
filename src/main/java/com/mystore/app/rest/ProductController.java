package com.mystore.app.rest;

import com.mystore.app.entity.Product;
import com.mystore.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product p = productService.addProduct(product);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        Product p = productService.getProduct(id);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        Product p = productService.updateProduct(id, product);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        String message = productService.deleteProduct(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // TODO: API to search products by name
    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProductByName(@RequestParam ("name") String name){
    	List<Product> p = productService.findProductByName(name);
    	if(p.isEmpty()) {
    		return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    	}
    	
    		return new ResponseEntity<>(p, HttpStatus.OK);
    
    }


    // TODO: API to filter products by category
    @GetMapping("/filter/category")
    public ResponseEntity<List<Product>> getProductByCategory(@RequestParam ("category") String category){
    	List<Product> p = productService.findProductByCategory(category);
    	
    	if(p.isEmpty()) {
    		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    	}
    	
    	return new ResponseEntity<List<Product>>(p, HttpStatus.OK);
    	
    }


    // TODO: API to filter products by price range
    
    @GetMapping("/filter/price")
    public ResponseEntity<List<Product>> getProductByPriceRange(@RequestParam ("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice)
    {
    	List<Product> p = productService.filterByPriceRange(minPrice, maxPrice);
    	if(p.isEmpty()) {
    		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    	}
  
    	return new ResponseEntity<List<Product>>(p, HttpStatus.OK);
    
    }


    
    


    // TODO: API to filter products by stock quantity range
    @GetMapping("/filter/stock")
    public ResponseEntity<List<Product>> getProductByStockRange(@RequestParam ("minStock") double minStock, @RequestParam("maxStock") double maxStock)
    {
    	List<Product> p = productService.filterByStockRange(minStock, maxStock);
    	if(p.isEmpty()) {
    		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    	}
    
    	return new ResponseEntity<List<Product>>(p, HttpStatus.OK);
    	
    }

}
