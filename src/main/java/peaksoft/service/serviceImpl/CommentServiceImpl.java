package peaksoft.service.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.request.CommentRequest;
import peaksoft.dto.response.BasketResponse;
import peaksoft.dto.response.CommentResponse;
import peaksoft.entity.Basket;
import peaksoft.entity.Comment;
import peaksoft.entity.User;
import peaksoft.exceptions.MyException;
import peaksoft.repository.CommentRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.CommentService;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    private User getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new NullPointerException("User not found!!!"));
        return user;
    }
    @Override
    public CommentResponse saveComment(CommentRequest commentRequest) {
        User authenticationUser = getAuthentication();
        Comment comment = Comment.builder().user(authenticationUser).comment(commentRequest.comment()).createdDate(ZonedDateTime.now()).build();
        commentRepository.save(comment);
        authenticationUser.getComments().add(comment);
        return CommentResponse.builder().id(comment.getId()).comment(comment.getComment()).createdDate(comment.getCreatedDate()).build();
    }

    @Override
    public CommentResponse getById(Long userId) {
        return commentRepository.getByIdComment(userId).orElseThrow(()->new NullPointerException("User with id: " + userId + "not found"));
    }

    @Override
    public List<CommentResponse> getAll() {
//        return commentRepository.getAllComment();
        User authenticationUser = getAuthentication();
        List<CommentResponse> userComments = new ArrayList<>();
        List<Comment> all = commentRepository.findAll();
        for (Comment c:all){
          if (c.getUser().equals(authenticationUser)){
              CommentResponse commentResponse = CommentResponse.builder().id(c.getId()).comment(c.getComment()).createdDate(c.getCreatedDate()).build();
              userComments.add(commentResponse);
          }
        }
        return userComments;
    }

    @Override
    public CommentResponse update(Long id, CommentRequest commentRequest) {
        User authenticationUser = getAuthentication();
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Comment with id: " +id+" not found"));

        if (!authenticationUser.equals(comment.getUser())){
            throw new BadCredentialsException("You can't update this comment");
        }
        Comment updateCommet = Comment.builder().comment(commentRequest.comment()).build();
        commentRepository.save(updateCommet);
        return CommentResponse.builder().id(comment.getId()).comment(comment.getComment()).createdDate(comment.getCreatedDate()).build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        try {
            if (commentRepository.existsById(id)) {
                commentRepository.deleteById(id);
                return  SimpleResponse.builder()
                        .status(HttpStatus.OK)
                        .message("Comment with Id deleted")
                        .build();
            } else throw new MyException("Comment with id: " + id + "is not found");
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
