package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.ProductResponse;
import peaksoft.entity.Product;
import peaksoft.exceptions.MyException;
import peaksoft.repository.ProductRepository;
import peaksoft.service.ProductService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse saveProduct(ProductRequest productRequest) {

        try {
            Product product=new Product();
            product.setName(productRequest.name());
//            product.setPrice(productRequest.price());
            if (productRequest.price()!= 0) {
                product.setPrice(productRequest.price());
            } else throw new MyException("Product price must not be empty");
            product.setImages(productRequest.images());
            product.setCharacteristic(productRequest.characteristic());
            product.setIsFavorite(false);
            product.setMadeIn(productRequest.madeIn());
            product.setCategory(productRequest.category());
            productRepository.save(product);
            return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getImages(), product.getCharacteristic(),product.getMadeIn(),product.getCategory(),product.getIsFavorite());
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return  null;
    }

    @Override
    public ProductResponse getById(Long brandId) {
            return productRepository.getByIdProduct(brandId).orElseThrow(() -> new NullPointerException("Brand with id: " + brandId + "not found"));
    }

    @Override
    public List<ProductResponse> getAll(Long brandId) {
        return productRepository.getAll(brandId);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest productRequest) {
        try {
           Product product=productRepository.findById(id).orElseThrow(() -> new MyException("Product with id: \" + id + \" is not found"));
            product.setName(productRequest.name());
            product.setPrice(productRequest.price());
            product.setImages(productRequest.images());
            product.setCharacteristic(productRequest.characteristic());
            product.setMadeIn(productRequest.madeIn());
            product.setCategory(productRequest.category());
            productRepository.save(product);
            return  ProductResponse.builder().id(product.getId()).name(product.getName()).price(product.getPrice()).images(product.getImages()).characteristic(product.getCharacteristic()).madeIn(product.getMadeIn()).category(product.getCategory()).build();
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public SimpleResponse delete(Long id) {
       try {
           if(productRepository.existsById(id)){
               productRepository.deleteById(id);
               return SimpleResponse.builder().status(HttpStatus.OK).message("Product with Id deleted").build();
           }else throw new MyException("Product with id:" + id + "is not found");
       }catch (MyException e){
           System.out.println(e.getMessage());
       }
       return null;
    }
}