package j.jav25.apiversioning.service;

import j.jav25.apiversioning.dto.ProductCreateRequest;
import j.jav25.apiversioning.dto.ProductResponse;
import j.jav25.apiversioning.dto.ProductResponseV2;
import j.jav25.apiversioning.dto.ProductResponseV3;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private final Map<Long, ProductResponse> products = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(4);

    public ProductService(){
        products.put(1L, new ProductResponse(
                1L,
                "Laptop",
                "High performance laptop",
                BigDecimal.valueOf(75000),
                10
        ));

        products.put(2L, new ProductResponse(
                2L,
                "Smartphone",
                "Android smartphone",
                BigDecimal.valueOf(25000),
                25
        ));

        products.put(3L, new ProductResponse(
                3L,
                "Headphones",
                "Wireless headphones",
                BigDecimal.valueOf(3000),
                50
        ));
    }

    public ProductResponse getProductById(Long id){
        return products.get(id);
    }

    public ProductResponseV2 getProductByIdV2(Long id) {
        ProductResponse product = products.get(id);
        return new ProductResponseV2(
                product.id(),
                product.name(),
                product.price(),
                "Electronics",
                product.quantity() > 0
        );
    }

    public ProductResponseV3 getProductByIdV3(Long id) {
        ProductResponse product = products.get(id);
        return new ProductResponseV3(
                product.id(),
                product.name() + " - Electronics",
                product.price(),
                "Electronics",
                product.quantity(),
                List.of("new", "trending", "popular"),
                LocalDateTime.now()
        );
    }

    public List<ProductResponse> getAllProducts(){
        return new ArrayList<>(products.values());
    }

    public List<ProductResponseV2> getAllProductsV2(){
        return products.values()
                .stream()
                .map(product -> new ProductResponseV2(
                        product.id(),
                        product.name(),
                        product.price(),
                        "Electronics",
                        product.quantity() > 0
                ))
                .toList();
    }

    public List<ProductResponseV3> getAllProductsV3(){
        return products.values()
                .stream()
                .map(product -> new ProductResponseV3(
                        product.id(),
                        product.name() + " - Electronics",
                        product.price(),
                        "Electronics",
                        product.quantity(),
                        List.of("new", "trending", "popular"),
                        LocalDateTime.now()
                ))
                .toList();
    }


    public ProductResponse createProduct(ProductCreateRequest request){

        Long id = idGenerator.getAndIncrement();

        ProductResponse productResponse = new ProductResponse(
                id,
                request.name(),
                request.description(),
                request.price(),
                request.quantity()
        );

        products.put(id, productResponse);

        return productResponse;
    }

    public ProductResponseV2 createProductV2(ProductCreateRequest request){
        ProductResponse product = createProduct(request);

        return new ProductResponseV2(
                product.id(),
                product.name(),
                product.price(),
                "Electronics",
                product.quantity() > 0
        );
    }

    public ProductResponseV3 createProductV3(ProductCreateRequest request){
        ProductResponse product = createProduct(request);

        return new ProductResponseV3(
                product.id(),
                product.name() + " - Electronics",
                product.price(),
                "Electronics",
                product.quantity(),
                List.of("new", "trending", "popular"),
                LocalDateTime.now()
        );
    }
}
