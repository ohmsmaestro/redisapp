package com.ohms.redisapp;

import com.ohms.redisapp.entity.Product;
import com.ohms.redisapp.repository.ProductDao;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/product")
public class RedisappApplication {
    private final ProductDao dao;

    public RedisappApplication(ProductDao dao) {
        this.dao = dao;
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return dao.save(product);
    }

    @GetMapping
    public List<Product> getProducts(){
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") int id){
        return dao.findProductById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable("id") int id){
        return dao.deleteProduct(id);
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisappApplication.class, args);
    }

}
