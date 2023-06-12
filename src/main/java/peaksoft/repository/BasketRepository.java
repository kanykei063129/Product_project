package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.AllBasketResponse;
import peaksoft.dto.response.BasketResponse;
import peaksoft.entity.Basket;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket,Long> {
//    @Query(value = "select new peaksoft.dto.response.BasketResponse(b.id,b.products,b.user) from Basket b where b.id=:Id")
//    Optional<BasketResponse>getBasketById(Long Id);//userid
@Query("SELECT NEW peaksoft.dto.response.BasketResponse(p.name, COUNT(p.id), SUM(p.price)) FROM Basket b JOIN b.products p WHERE b.user.id = :userId")
BasketResponse getBasket(@Param("userId") Long userId);
//    @Query("SELECT NEW peaksoft.dto.response.AllBasketResponse (u.email,CAST(count (p.id)as int),sum(p.price)) FROM Basket b JOIN b.user u JOIN b.products p")
//    List<AllBasketResponse> getAllBasket();
}
