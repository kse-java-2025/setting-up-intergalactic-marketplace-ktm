package com.example.spacecatsmarket.web;

import com.example.spacecatsmarket.service.CosmoCatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cosmo-cats")
public class CosmoCatController {

    private final CosmoCatService cosmoCatService;

    public CosmoCatController(CosmoCatService cosmoCatService) {
        this.cosmoCatService = cosmoCatService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getCosmoCats() {
        List<String> cats = cosmoCatService.getCosmoCats();
        return ResponseEntity.ok(cats);
    }
}

