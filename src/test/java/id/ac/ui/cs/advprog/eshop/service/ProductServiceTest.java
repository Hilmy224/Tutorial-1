package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testServiceCreate() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        
        when(productRepository.create(product)).thenReturn(product);

        Product savedProduct = productServiceImpl.create(product);
        
        Assertions.assertThat(savedProduct).isNotNull();
        verify(productRepository,times(1)).create(product);
    }

    @Test
    void testServiceFind() {
        Product product = new Product();
        product.setProductId("Secret ID");
        product.setProductName("Twig");
        product.setProductQuantity(100);
        

        when(productRepository.findProduct(product.getProductId())).thenReturn(product);
        Product findSavedProduct = productServiceImpl.findProduct("Secret ID");
        
        assertEquals(findSavedProduct.getProductId(), product.getProductId());
    }

    @Test
    void testServiceFindAllIfEmpty() {
        List<Product> products = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(products.iterator());
        Iterator<Product> productIterator = productServiceImpl.findAll().iterator();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("Ultra");
        product1.setProductName("V1");
        product1.setProductQuantity(100);
       

        Product product2 = new Product();
        product2.setProductId("Kill");
        product2.setProductName("V2");
        product2.setProductQuantity(50);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        when(productRepository.findAll()).thenReturn(products.iterator());

        List<Product> productServiceList = productServiceImpl.findAll();

        assertEquals(productServiceList.size(), 2);
        assertTrue(productServiceList.contains(product1));
        assertTrue(productServiceList.contains(product1));
        assertEquals(productServiceList.get(1).getProductId(), product2.getProductId());
    }

    @Test
    void testServiceEditProduct() {
        Product editThisProduct = new Product();
        editThisProduct.setProductId("Gluttony");
        editThisProduct.setProductName("Gabriel");
        editThisProduct.setProductQuantity(7);

        doNothing().when(productRepository).editProduct("Gluttony", "Gabriel Phase II", 29);
        
        productServiceImpl.editProduct("Gluttony", "Gabriel Phase II", 29);
        editThisProduct.setProductName("Gabriel Phase II");
        editThisProduct.setProductQuantity(29);
        
        assertEquals("Gabriel Phase II", editThisProduct.getProductName());
        assertEquals(29, editThisProduct.getProductQuantity());
        verify(productRepository).editProduct("Gluttony", "Gabriel Phase II", 29);

    }

    @Test
    void testServiceDeleteProduct() {
        Product deleteThisProduct = new Product();
        deleteThisProduct.setProductId("Wrath");
        deleteThisProduct.setProductName("Sissyphus Prime");
        deleteThisProduct.setProductQuantity(62);

        doNothing().when(productRepository).deleteProduct("Wrath");
        
        productServiceImpl.deleteProduct("Wrath");
        
        assertEquals("Sissyphus Prime", deleteThisProduct.getProductName());
        assertEquals(62, deleteThisProduct.getProductQuantity());
        verify(productRepository).deleteProduct("Wrath");

    }


}
