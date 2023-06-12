package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.ProductResponse;
import peaksoft.dto.response.ProductResponseAll;
import peaksoft.enums.Category;
import peaksoft.repository.ProductRepository;

import java.util.List;

public interface ProductService {
    ProductResponse saveProduct(ProductRequest productRequest);
    ProductResponse getById(Long brandId);
    List<ProductResponse> getAll(Long brandId);
    ProductResponse update(Long id, ProductRequest productRequest);
    SimpleResponse delete(Long id);
//    List<ProductResponse> getAllFilter(Category category,int price);
//    ProductResponseAll getAllComment();
//    String favoriteUnfavoriteProduct(Long productId, Boolean isFavorite);
//    ProductResponse getFavorite(Long userId,Boolean isFavorite);
}
