package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.BrandRequest;
import peaksoft.dto.response.BrandResponse;

import java.util.List;

public interface BrandService {
    BrandResponse saveBrand(BrandRequest brandRequest);
    BrandResponse getById(Long id);
    List<BrandResponse> getAll();
    BrandResponse update(Long id,BrandRequest brandRequest);
    SimpleResponse delete(Long id);
}
