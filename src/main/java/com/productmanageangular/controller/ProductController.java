package com.productmanageangular.controller;

import com.productmanageangular.model.Product;
import com.productmanageangular.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Optional;
@CrossOrigin("*")
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<Product>> showAll(){
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public  ResponseEntity<Product>findById(@PathVariable Long id){
        Optional<Product> productOptional = productService.findById(id);
        if(!productOptional.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
        return new ResponseEntity<>(productOptional.get(),HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id){
        Optional<Product> productOptional = productService.findById(id);
        product.setId(productOptional.get().getId());
        productService.save(product);
        if(!productOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return  new ResponseEntity<>(HttpStatus.OK);

    }

}
