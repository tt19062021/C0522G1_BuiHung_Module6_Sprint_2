package com.example.controller;

import com.example.dto.IBeerDto;
import com.example.jwt.JwtTokenUtil;
import com.example.payload.request.LoginRequest;
import com.example.payload.request.LoginResponse;
import com.example.service.IBeerService;
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

import javax.validation.Valid;
import java.util.List;
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
    @GetMapping("/list")
    public ResponseEntity<Page<IBeerDto>> findAllBeer(Pageable pageable
//                                                      ,@RequestParam(value = "name",defaultValue = "") String name
    ) {
        Page<IBeerDto> iBeerDtoPage = beerService.findAllBeer(pageable);
        if (iBeerDtoPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(iBeerDtoPage, HttpStatus.OK);
        }
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
}
