package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(Product product) {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product", new Product());
        productService.save(product);
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView showList() {
        Iterable<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.remove(id);
        return "redirect:/products";
    }
    @GetMapping("/edit/{id}")
public ModelAndView editProduct(@PathVariable Long id){
        Optional<Product> products = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("product",products.get());
        return modelAndView;
    }
    @PostMapping("/edit/{id}")
    public ModelAndView update(@ModelAttribute("product") Product product){
        ModelAndView modelAndView = new ModelAndView("/edit");
        productService.save(product);
        modelAndView.addObject("product",product);
        return modelAndView;

    }
}
