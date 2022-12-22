package com.example.controller;

import com.example.dto.ICartDto;
import com.example.dto.IBeerDto;
import com.example.dto.ITotalDto;
import com.example.jwt.JwtTokenUtil;
import com.example.model.beer.Image;
import com.example.model.cart.Cart;
import com.example.model.customer.Customer;
import com.example.payload.request.LoginRequest;
import com.example.payload.request.LoginResponse;
import com.example.service.IBeerService;
import com.example.service.ICustomerService;
import com.example.service.IImageService;
import com.example.service.ICartService;
import com.example.service.decentralization.impl.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/beer")
public class BeerController {
    @Autowired
    private IBeerService beerService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private IImageService imageService;

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICartService cartService;

    @GetMapping("/list")
    public ResponseEntity<Page<IBeerDto>> findAllBeer(
            Pageable pageable,
            @RequestParam(value = "nameSearch", defaultValue = "", required = false) String nameSearch,
            @RequestParam(value = "startPrice", defaultValue = "0", required = false) Integer startPrice,
            @RequestParam(value = "endPrice", defaultValue = "0", required = false) Integer endPrice) {
        Page<IBeerDto> iBeerDtoPage;
        if (endPrice == 0) {
            iBeerDtoPage = beerService.findAllBeer(pageable, nameSearch);
            System.out.println("1");
        } else {
            iBeerDtoPage = beerService.findAllBeerByPrice(pageable, nameSearch, startPrice, endPrice);
            System.out.println("2");
        }

        if (iBeerDtoPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(iBeerDtoPage, HttpStatus.OK);
    }
    @GetMapping("/find-id-beer/{id}")
    public ResponseEntity<?> findBeerById(@PathVariable Integer id) {
        Optional<IBeerDto> beerDtoOptional = beerService.findBeerById(id);
        if (beerDtoOptional.isPresent()) {
            return new ResponseEntity<>(beerDtoOptional, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/find-all-image/{id}")
    public ResponseEntity<?> findAllImage(@PathVariable Integer id) {
        List<Image> imageList = imageService.findAllImage(id);
        if (imageList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<>(imageList, HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(loginRequest.getUsername());
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = myUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                new LoginResponse(
                        jwt,
                        myUserDetails.getUsername(),
                        roles));
    }

    @GetMapping("/cart")
    public ResponseEntity<List<ICartDto>> getCartList(@RequestParam String username) {
        Customer customer = customerService.findCustomerByUsername(username);
        List<ICartDto> cartDtos = cartService.getCartList(customer.getId());
        if (cartDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cartDtos, HttpStatus.OK);
    }

    @GetMapping("/total-bill")
    public ResponseEntity<ITotalDto> getTotalBill(@RequestParam String username) {
        Customer customer = customerService.findCustomerByUsername(username);
        ITotalDto totalBill = beerService.getTotalBill(customer.getId());
        if (totalBill == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(totalBill, HttpStatus.OK);
    }

    @PostMapping("/cart-update")
    public ResponseEntity<?> updateCart(@RequestParam Integer id,
                                        @RequestParam String username) {
        IBeerDto iBeerDto = beerService.findById(id, username);
        Customer customer = customerService.findCustomerByUsername(username);
        if (iBeerDto == null ) {
            beerService.insertProductToCart(id, customer.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        beerService.addToCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/qty-update")
    public ResponseEntity<?> updateQty(@RequestParam Integer id,
                                       @RequestParam(name = "quantity") Integer quantity,
                                       @RequestParam String username) {
        Customer customer = customerService.findCustomerByUsername(username);
        if (quantity == 0) {
            beerService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        beerService.updateQty(id, quantity, customer.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/remove-cart/{id}")
    public ResponseEntity<Cart> removeCart(@PathVariable("id") Integer id) {
        cartService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/payment/{username}")
    public ResponseEntity<Cart> payment(@PathVariable("username") String username) {
        Customer customer = customerService.findCustomerByUsername(username);
        cartService.payment(customer.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/history/{username}")
    public ResponseEntity<List<ICartDto>> showHistory(@PathVariable("username") String username) {
        List<ICartDto> cartDtoList = cartService.historyShopping(username);
        if (cartDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cartDtoList, HttpStatus.OK);
    }
}
