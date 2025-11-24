package com.example.spacecatsmarket.service;

import com.example.spacecatsmarket.exception.FeatureNotAvailableException;
import com.example.spacecatsmarket.featuretoggle.FeatureToggleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
class CosmoCatServiceTest {

    @Autowired
    private CosmoCatService cosmoCatService;

    @MockitoBean
    private FeatureToggleService featureToggleService;

    @Test
    void getCosmoCats_whenFeatureEnabled_shouldReturnCatsList() {
        when(featureToggleService.isFeatureEnabled("cosmoCats")).thenReturn(true);

        List<String> cats = cosmoCatService.getCosmoCats();

        assertThat(cats)
                .isNotNull()
                .hasSize(5)
                .containsExactly("Cosmo", "Stellar", "Nebula", "Galaxy", "Orion");
    }

    @Test
    void getCosmoCats_whenFeatureDisabled_shouldThrowException() {
        when(featureToggleService.isFeatureEnabled("cosmoCats")).thenReturn(false);

        assertThatThrownBy(() -> cosmoCatService.getCosmoCats())
                .isInstanceOf(FeatureNotAvailableException.class)
                .hasMessage("Feature 'cosmoCats' is not available");
    }
}