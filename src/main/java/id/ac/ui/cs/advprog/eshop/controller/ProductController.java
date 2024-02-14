package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public String index(){
        return "HomePage";
    }

    @GetMapping("/product/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product" , product);
        return "CreateProduct";
    }

    @PostMapping("/product/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/product/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    @GetMapping("/product/edit/{id}")
    public String editProductPage(@PathVariable("id") String id, Model model){
        Product targetProduct=service.findProduct(id);

        model.addAttribute("product",targetProduct);
        return "EditProduct";
    }

    @PostMapping("/product/edit")
    public String postEditProduct(@ModelAttribute Product product, Model model){
        service.editProduct(product.getProductId(),product.getProductName(), product.getProductQuantity());
        return "redirect:list";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable ("id") String id, Model model){
        service.deleteProduct(id);
        return "redirect:../list";
    }
}