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
import peaksoft.dto.request.FavoriteRequest;
import peaksoft.dto.response.BrandResponse;
import peaksoft.dto.response.CommentResponse;
import peaksoft.dto.response.FavoriteResponse;
import peaksoft.entity.Brand;
import peaksoft.entity.Comment;
import peaksoft.entity.Favorite;
import peaksoft.entity.User;
import peaksoft.exceptions.MyException;
import peaksoft.repository.FavoriteRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.FavoriteService;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;

    private User getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new NullPointerException("User not found!!!"));
        return user;
    }

    @Override
    public FavoriteResponse saveFavorite(FavoriteRequest favoriteRequest) {
        User authenticationUser = getAuthentication();
        Favorite favorite = Favorite.builder().user(authenticationUser).product(favoriteRequest.product()).build();
        favoriteRepository.save(favorite);
        authenticationUser.getFavorites().add(favorite);
        return FavoriteResponse.builder().id(favorite.getId()).userId(favorite.getUser()).product(favorite.getProduct()).build();
    }
    @Override
    public FavoriteResponse getById(Long id) {
        try {
            Favorite favorite = favoriteRepository.findById(id).orElseThrow(() -> new MyException("Favorite with id: " + id + "not found"));
            return FavoriteResponse.builder().id(favorite.getId()).userId(favorite.getUser()).product(favorite.getProduct()).build();
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<FavoriteResponse> getAll() {
        User authenticationUser = getAuthentication();
        List<FavoriteResponse> userFavorite = new ArrayList<>();
        List<Favorite> all = favoriteRepository.findAll();
        for (Favorite f:all){
            if (f.getUser().equals(authenticationUser)){
                FavoriteResponse favoriteResponse = FavoriteResponse.builder().id(f.getId()).userId(f.getUser()).product(f.getProduct()).build();
                userFavorite.add(favoriteResponse);
            }
        }
        return userFavorite;
    }

    @Override
    public FavoriteResponse update(Long id, FavoriteRequest favoriteRequest) {
        User authenticationUser = getAuthentication();
        Favorite favorite = favoriteRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Favorite with id: " +id+" not found"));

        if (!authenticationUser.equals(favorite.getUser())){
            throw new BadCredentialsException("You can't update this favorite");
        }
        Favorite updateFavorite = Favorite.builder().product(favoriteRequest.product()).build();
        favoriteRepository.save(updateFavorite);
        return FavoriteResponse.builder().id(favorite.getId()).userId(favorite.getUser()).product(favorite.getProduct()).build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        try {
            if (favoriteRepository.existsById(id)) {
                favoriteRepository.deleteById(id);
                return  SimpleResponse.builder()
                        .status(HttpStatus.OK)
                        .message("Favorite with Id deleted")
                        .build();
            } else throw new MyException("Favorite with id: " + id + "is not found");
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}