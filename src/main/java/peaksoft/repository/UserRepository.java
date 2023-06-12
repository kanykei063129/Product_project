package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.UserResponse;
import peaksoft.entity.User;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);
    @Query("SELECT NEW peaksoft.dto.response.UserResponse (u.id,u.firstName,u.lastName,u.email) FROM User u WHERE u.email = :email")
    Optional<UserResponse> getUserEmail(String email);

    @Query("SELECT NEW peaksoft.dto.response.UserResponse(u.id,u.firstName,u.lastName,u.email) FROM User u WHERE u.role <> 'ADMIN'")
    List<UserResponse> getAllUser();

    @Query("SELECT NEW peaksoft.dto.response.UserResponse (u.id,u.firstName,u.lastName,u.email) FROM User u WHERE u.id = :id")
    Optional<UserResponse> gerUserId(Long id);
}
