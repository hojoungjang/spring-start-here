package com.github.hojoungjang.pt2_implementation.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.hojoungjang.pt2_implementation.model.Product;
import com.github.hojoungjang.pt2_implementation.service.ProductService;

/*
 * To make request parameters optional pass `required` option
 * to @RequestParam annotaions.
 */

@Controller
public class MainController {
    private final ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getProducts(Model page) {
        List<Product> products = productService.findAll();
        page.addAttribute("products", products);
        return "products.html";
    }

    @PostMapping("/products")
    public String createProduct(
        @RequestParam() String name,
        @RequestParam() double price,   // Can change this two lines to `Product product`
        Model page
    ) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        productService.addProduct(product);
        page.addAttribute("products", productService.findAll());
        return "products.html";
    }

    @RequestMapping("/home")
    public String home(
        @RequestParam(required=false, defaultValue="World") String username,
        @RequestParam(required=false, defaultValue="green") String color,
        Model page
    ) {
        page.addAttribute("username", username);
        page.addAttribute("color", color);
        return "home_template.html";
    }

    @RequestMapping("/home2/{username}/{color}")
    public String home2(
        @PathVariable String username,
        @PathVariable String color,
        Model page
    ) {
        page.addAttribute("username", username);
        page.addAttribute("color", color);
        return "home_template.html";
    }
}
