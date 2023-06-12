package peaksoft.repository;

import jakarta.persistence.Lob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.CommentResponse;
import peaksoft.dto.response.ProductResponse;
import peaksoft.entity.Product;
import peaksoft.enums.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select new peaksoft.dto.response.ProductResponse(p.id,p.name,p.price,p.images,p.characteristic,p.madeIn,p.category,p.isFavorite) from Product p where p.brand.id = :brandId")
    Optional<ProductResponse> getByIdProduct(@Param("brandId") Long brandId);
    @Query(value = "select new peaksoft.dto.response.ProductResponse(p.id,p.name,p.price,p.images,p.characteristic,p.madeIn,p.category,p.isFavorite)from Product p where p.brand.id=:brandId")
    List<ProductResponse> getAll(Long brandId);
}