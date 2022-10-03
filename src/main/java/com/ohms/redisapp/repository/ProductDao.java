package com.ohms.redisapp.repository;

import com.ohms.redisapp.entity.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    private final RedisTemplate template;

    public ProductDao(@Qualifier("template") RedisTemplate template) {
        this.template = template;
    }

    public Product save(Product product){
        template.opsForHash().put("product", product.getId(), product);
        return product;
    }


    public List<Product> findAll(){
        return template.opsForHash().values("product");
    }

    public Product findProductById(int id){
        return (Product) template.opsForHash().get("product", id);
    }

    public String deleteProduct(int id){
         template.opsForHash().delete("product", id);
        return "Product Removed";
    }
}
