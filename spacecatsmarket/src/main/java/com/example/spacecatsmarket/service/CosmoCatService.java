package com.example.spacecatsmarket.service;

import com.example.spacecatsmarket.featuretoggle.FeatureToggle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CosmoCatService {

    @FeatureToggle(featureName = "cosmoCats")
    public List<String> getCosmoCats() {
        return List.of("Cosmo", "Stellar", "Nebula", "Galaxy", "Orion");
    }
}

