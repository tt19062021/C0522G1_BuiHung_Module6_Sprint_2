package com.example.controller;

import com.example.dto.IBeerDto;
import com.example.service.IBeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/beer")
public class BeerController {
    @Autowired
    private IBeerService beerService;

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
}
