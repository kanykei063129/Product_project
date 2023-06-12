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
import peaksoft.dto.request.BasketRequest;
import peaksoft.dto.response.AllBasketResponse;
import peaksoft.dto.response.BasketResponse;
import peaksoft.entity.Basket;
import peaksoft.entity.User;
import peaksoft.exceptions.MyException;
import peaksoft.repository.BasketRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.BasketService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;
    private final UserRepository userRepository;

//    private User getAuthentication() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new NullPointerException("User not found!!!"));
//        return user;
//    }

    @Override
    public boolean saveBasket(String email) {
//        User authenticationUser = getAuthentication();
//        Basket basket = Basket.builder().user(authenticationUser).products(basketRequest.productsId()).build();
//        basketRepository.save(basket);
//        authenticationUser.setBasket(basket);
//        return BasketResponse.builder().id(basket.getId()).userId(basket.getUser()).productsId(basket.getProducts()).build();
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new NullPointerException("User with email: %s not found".formatted(email)));
        Basket basket = Basket.builder().user(user).build();
        user.setBasket(basket);
        basketRepository.save(basket);
        return false;
    }

    @Override
    public BasketResponse getById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new NullPointerException("User email: %s not found".formatted(email)));
        return basketRepository.getBasket(user.getId());    }

    @Override
    public List<AllBasketResponse> getAll() {
        return basketRepository.getAllBasket();
    }

//    @Override
//    public BasketResponse update(Long id, BasketRequest basketRequest) {
//        User authenticationUser = getAuthentication();
//        Basket basket = basketRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Basket with id: " + id + " not found"));
//
//        if (!authenticationUser.equals(basket.getUser())) {
//            throw new BadCredentialsException("You can't update this basket");
//        }
//        Basket updateBasket = Basket.builder().products(basketRequest.productsId()).build();
//        basketRepository.save(updateBasket);
//        return BasketResponse.builder().id(basket.getId()).userId(basket.getUser()).productsId(basket.getProducts()).build();
//    }
//
//    @Override
//    public SimpleResponse delete(Long id) {
//        try {
//            if (basketRepository.existsById(id)) {
//                basketRepository.deleteById(id);
//                return SimpleResponse.builder().status(HttpStatus.OK).message("Basket deleted successfully!").build();
//            } else throw new MyException("Basket with id: " + id + "is not found");
//        } catch (MyException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }
}
