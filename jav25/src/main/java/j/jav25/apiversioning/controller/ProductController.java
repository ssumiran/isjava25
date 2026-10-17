//package j.jav25.apiversioning.controller;
//
//import j.jav25.apiversioning.dto.ProductCreateRequest;
//import j.jav25.apiversioning.dto.ProductResponse;
//import j.jav25.apiversioning.dto.ProductResponseV2;
//import j.jav25.apiversioning.dto.ProductResponseV3;
//import j.jav25.apiversioning.service.ProductService;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/products")
//public class ProductController {
//
//    private final ProductService productService;

	  //Constructor injection
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }
//
//    @GetMapping(value = "/{id}", version = "1.0")
//    public ProductResponse getProductByIdV1(@PathVariable Long id){
//        return productService.getProductById(id);
//    }
//
//    @GetMapping(value = "/{id}", version = "2.0")
//    public ProductResponseV2 getProductByIdV2(@PathVariable Long id){
//        return productService.getProductByIdV2(id);
//    }
//
//    @GetMapping(value = "/{id}", version = "3.0")
//    public ProductResponseV3 getProductByIdV3(@PathVariable Long id){
//        return productService.getProductByIdV3(id);
//    }
//
//    @GetMapping(version = "1.0")
//    public List<ProductResponse> getAllProductsV1(){
//        return productService.getAllProducts();
//    }
//
//    @GetMapping(version = "2.0")
//    public List<ProductResponseV2> getAllProductsV2(){
//        return productService.getAllProductsV2();
//    }
//
//    @GetMapping(version = "3.0")
//    public List<ProductResponseV3> getAllProductsV3(){
//        return productService.getAllProductsV3();
//    }
//
//    @PostMapping(version = "1.0")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ProductResponse createProductV1(@Valid @RequestBody ProductCreateRequest request){
//        return productService.createProduct(request);
//    }
//
//    @PostMapping(version = "2.0")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ProductResponseV2 createProductV2(@Valid @RequestBody ProductCreateRequest request){
//        return productService.createProductV2(request);
//    }
//
//    @PostMapping(version = "3.0")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ProductResponseV3 createProductV3(@Valid @RequestBody ProductCreateRequest request){
//        return productService.createProductV3(request);
//    }
//}
