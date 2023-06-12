package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.CommentResponse;
import peaksoft.entity.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
//    @Query(value = "select new peaksoft.dto.response.CommentResponse(c.id,c.comment,c.createdDate)from Comment c")
//    List<CommentResponse> getAllComment();

    @Query(value = "select new peaksoft.dto.response.CommentResponse(c.id,c.comment,c.createdDate)from Comment c where c.user.id=:userId")
    Optional<CommentResponse> getByIdComment(Long userId);
}
