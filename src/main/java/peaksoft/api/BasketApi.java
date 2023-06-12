package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.BasketRequest;
import peaksoft.dto.response.AllBasketResponse;
import peaksoft.dto.response.BasketResponse;
import peaksoft.repository.BasketRepository;
import peaksoft.service.BasketService;

import java.util.List;

@RestController
@RequestMapping("/baskets")
@RequiredArgsConstructor
public class BasketApi {
    private final BasketService basketService;

    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping("/save-basket")
    boolean saveBasket(@RequestParam String email){
        return basketService.saveBasket(email);
    }
    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/{Id}")
    BasketResponse getById(@PathVariable Long Id){
        return basketService.getById(Id);
    }
    @GetMapping
    List<AllBasketResponse> getAll() {
        return basketService.getAll();
    }
}
