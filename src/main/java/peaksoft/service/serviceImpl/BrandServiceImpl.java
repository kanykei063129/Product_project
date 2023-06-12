package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.BrandRequest;
import peaksoft.dto.response.BrandResponse;
import peaksoft.entity.Brand;
import peaksoft.exceptions.MyException;
import peaksoft.repository.BrandRepository;
import peaksoft.service.BrandService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public BrandResponse saveBrand(BrandRequest brandRequest) {
        try {
            Brand brand = new Brand();
//            brand.setBrandName(brandRequest.brandName());
                if(brandRequest.brandName().length()==14 || brandRequest.brandName().length()==10){
                    brand.setBrandName(brandRequest.brandName());
                }else throw new MyException("The brand name must be 14 or 10 letters long.");
            brand.setImage(brandRequest.image());
            brandRepository.save(brand);
            return BrandResponse.builder().id(brand.getId()).brandName(brand.getBrandName()).image(brand.getImage()).build();
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public BrandResponse getById(Long id) {
        try {
            Brand brand = brandRepository.findById(id).orElseThrow(() -> new MyException("Brand with id: " + id + "not found"));
            return BrandResponse.builder().id(brand.getId()).brandName(brand.getBrandName()).image(brand.getImage()).build();
//        return brandRepository.getByBrandId(id).orElseThrow(() -> new MyException("Brand with id: " + id + "not found"));
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public List<BrandResponse> getAll() {
        return brandRepository.getAllBrand();
    }

    @Override
    public BrandResponse update(Long id, BrandRequest brandRequest) {
        try {
            Brand brand = brandRepository.findById(id).orElseThrow(() -> new MyException("Brand with id: " + id + "not found"));
            brand.setBrandName(brandRequest.brandName());
            brand.setImage(brandRequest.image());
            brandRepository.save(brand);
            return BrandResponse.builder().id(brand.getId()).brandName(brand.getBrandName()).image(brand.getImage()).build();
    }catch (MyException e){
        System.out.println(e.getMessage());
    }
        return null;
}


    @Override
    public SimpleResponse delete(Long id) {
        try {
            if (brandRepository.existsById(id)) {
                brandRepository.deleteById(id);
                return  SimpleResponse.builder()
                        .status(HttpStatus.OK)
                        .message("Brand with id deleted")
                        .build();
            } else throw new MyException("Brand with id: " + id + "is not found");
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
