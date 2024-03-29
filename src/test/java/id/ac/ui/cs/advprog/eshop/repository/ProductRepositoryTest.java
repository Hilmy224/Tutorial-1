package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);



        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
        
    }

    @Test
    void testCreateCases() {
        Product productExistingId = new Product();
        productExistingId.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productExistingId.setProductName("Boomb");
        productExistingId.setProductQuantity(200);
        productRepository.create(productExistingId);

        
        Product productNoId = new Product();
        productNoId.setProductName("Boomb");
        productNoId.setProductQuantity(200);
        productRepository.create(productNoId);
        

        assertNotNull(productExistingId.getProductId());
        assertNotNull(productNoId.getProductId());
    }




    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(),savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.editProduct("eb558e9f-1c39-460e-8860-71af6af63bd6","Giri Mini Sibi",200);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());

    }

    @Test
    void testEditProductFailed() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertThrows(NoSuchElementException.class,
                () -> productRepository.editProduct("Wrong ID","Giri Mini Sibi",200));
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("SecretId");
        product.setProductName("Waluigi");
        product.setProductQuantity(0101);
        productRepository.create(product);

        productRepository.deleteProduct("SecretId");
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());

    }

    @Test
    void testDeleteFailProduct() {
        Product product = new Product();
        product.setProductId("2024");
        product.setProductName("Just Luigi");
        product.setProductQuantity(1234);
        productRepository.create(product);

        assertThrows(NoSuchElementException.class, ()->productRepository.deleteProduct("2025"));
    }

}