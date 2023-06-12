package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.BrandResponse;
import peaksoft.entity.Brand;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
//    @Query(value = "select new peaksoft.dto.response.BrandResponse(b.id,b.brandName,b.image)from Brand b where b.id=:id")
//    Optional<BrandResponse> getByBrandId(Long id);

    @Query(value = "select new peaksoft.dto.response.BrandResponse(b.id,b.brandName,b.image)from Brand b")
    List<BrandResponse>getAllBrand();
}
