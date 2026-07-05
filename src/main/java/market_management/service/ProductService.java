package market_management.service;

import market_management.dto.ProductDTO;
import market_management.entity.Order;
import market_management.entity.Product;
import market_management.repository.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductService {
    ProductRepository productRepository=ProductRepository.getInstance();

    public static ProductService productService;
    private  ProductService(){}
    public static ProductService getInstance(){
        if (productService==null){
            productService=new ProductService();
        }
        return productService;
    }


    public boolean addProduct(ProductDTO dto) {
        if (dto.name().isBlank())  return false;
        if (dto.price()<0)  return false;
        if (dto.quantity()<0)  return false;
        Optional<Product> optional = productRepository.getProductByName(dto.name());
        if ( optional.isPresent()) return  false;

        Product product=new Product(UUID.randomUUID().toString(), dto.name(), dto.price(), dto.quantity());
        productRepository.saveProduct(product);
        return true;
    }

    public List<ProductDTO> showProducts() {
        return  productRepository.getList().stream()
                .map(product -> new ProductDTO(product.getName(), product.getPrice(), product.getQuantity())).toList();
    }


    public boolean deleteProduct(int choose) {
       return productRepository.delete(choose);
    }

    public boolean updatePrice(int index,Double newPrice) {
        if (newPrice<0) return  false;
        return  productRepository.update(index, newPrice);
    }

    public boolean updateQuantity(int index, int newQuantity) {
        if (newQuantity<0) return false;
        return  productRepository.updateQuantity(index,newQuantity);
    }

    public Optional<Product> searchProduct(String searchName) {
        if ( searchName.isBlank()) return Optional.empty();
        return productRepository.getProductByName(searchName);
    }

    public List<ProductDTO> filterProduct(Double minPrice, Double maxPrice) {
        if (maxPrice<minPrice) return Collections.emptyList();
        List<Product> list = productRepository.getList();
        return  list.stream().filter(product -> product.getPrice()>=minPrice && product.getPrice()<=maxPrice)
                .map(product ->new ProductDTO(product.getName(),product.getPrice(), product.getQuantity()) ).toList();
    }
}
