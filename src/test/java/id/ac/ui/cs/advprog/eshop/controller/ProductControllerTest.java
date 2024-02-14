package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> productList = Arrays.asList(new Product(), new Product());
        doReturn(productList).when(productService).findAll();

        mockMvc.perform(get("/product/list"))
            .andExpect(view().name("ProductList"))
            .andExpect(model().attribute("products", productList))
            .andExpect(status().isOk());
    }

    @Test
    void testNormalProductCreatePage() throws Exception {
        mockMvc.perform(get("/product/create"))
            .andExpect(view().name("CreateProduct"))
            .andExpect(model().attributeExists("product"))
            .andExpect(status().isOk());
    }

    @Test
    void testProductCreatePagePost() throws Exception {
        doReturn(new Product()).when(productService).create(any(Product.class));


        mockMvc.perform(post("/product/create")
            .param("productName", "Ya Hellow")
            .param("productQuantity","21"))
            .andExpect(redirectedUrl("list"))
            .andExpect(status().is3xxRedirection());
    }

    @Test
    void testNormalProductEditPage() throws Exception {
        Product editPageProduct=new Product();
        editPageProduct.setProductId("myId");
        editPageProduct.setProductName("Caestus");
        editPageProduct.setProductQuantity(122);

        doReturn(editPageProduct).when(productService).findProduct(editPageProduct.getProductId());

        mockMvc.perform(get("/product/edit").param("id",editPageProduct.getProductId()))
            .andExpect(view().name("EditProduct"))
            .andExpect(model().attributeExists("product"))
            .andExpect(status().isOk());
    }

    @Test
    void testEditProductPage() throws Exception {
        mockMvc.perform(post("/product/edit")
        .param("productName","Spiked Caestus")
        .param("productId","Edit Me")
        .param("productQuantity", "22222"))
        .andExpect(redirectedUrl("list"))
        .andExpect(status().is3xxRedirection());
    }

    @Test
    void testProductDeleteProduct() throws Exception {
        Product product = new Product();
        product.setProductName("MiniCraft");
        product.setProductQuantity(64);
        product.setProductId("DirtBlock");
        
        doNothing().when(productService).deleteProduct(product.getProductId());

        mockMvc.perform(get("/product/delete").param("id", product.getProductId()))
            .andExpect(redirectedUrl("../list"))
            .andExpect(status().is3xxRedirection());
    }

}
