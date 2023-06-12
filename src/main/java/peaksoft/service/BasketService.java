package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.BasketRequest;
import peaksoft.dto.response.AllBasketResponse;
import peaksoft.dto.response.BasketResponse;

import java.util.List;

public interface BasketService {
    boolean saveBasket(String email);
    BasketResponse getById();
    List<AllBasketResponse> getAll();
//    BasketResponse update(Long id,BasketRequest basketRequest);
//    SimpleResponse delete(Long id);
}
