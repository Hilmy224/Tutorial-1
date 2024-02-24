    package id.ac.ui.cs.advprog.eshop.repository;

    import id.ac.ui.cs.advprog.eshop.model.Product;
    import org.springframework.stereotype.Repository;

    import java.util.*;

    @Repository
    public class ProductRepository {
        private List<Product> productData = new ArrayList<>();

        public Product create(Product product) {
            if(product.getProductId() == null){
                UUID uuid = UUID.randomUUID();
                product.setProductId(uuid.toString());
            }
            productData.add(product);
            return product;
        }

        public Iterator<Product> findAll() {
            return productData.iterator();
        }
        public Product findProduct(String productId) {
            for(int i=0;i< productData.size();i++){
                if(productData.get(i).getProductId().equals(productId)){
                    return productData.get(i);
                }
            }

            throw new NoSuchElementException("The product with {" + productId+"} ID was not found");
        }

        public void editProduct(String productId,String productName,int productQuantity) {
            Product tempProduct=findProduct(productId);
            tempProduct.setProductName(productName);
            tempProduct.setProductQuantity(productQuantity);
        }

        public void deleteProduct(String productId){
            Product delProduct=findProduct(productId);
            productData.remove(delProduct);
        }
    }

