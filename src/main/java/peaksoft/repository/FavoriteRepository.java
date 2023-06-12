package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
}
