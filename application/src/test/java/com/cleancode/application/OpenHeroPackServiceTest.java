package com.cleancode.application;

import com.cleancode.application.ports.out.OpenHeroPackPersistence;
import com.cleancode.application.services.OpenHeroPackServiceImpl;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroPack;
import com.cleancode.domain.HeroRef;
import com.cleancode.domain.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OpenHeroPackServiceTest {

    @InjectMocks
    private OpenHeroPackServiceImpl service;

    @Mock
    private OpenHeroPackPersistence persistence;

    @Test
    void should_open_hero_pack() {
        final var mockPlayer = Player.builder()
            .id(1L)
            .name("mockPlayer1")
            .deck(new ArrayList<Hero>())
            .tokens(4)
            .build();
        final var mockPack = new HeroPack(
            1L,
            "Argent",
            1,
            3,
            0.75f,
            0.2f,
            0.05f
        );
        final var mockHero1 = new Hero(
            1L,
            new HeroRef(1L, "Tank", 1200, 120, 22, "Légendaire"),
            0,
            1
        );
        final var mockHero2 = new Hero(
            2L,
            new HeroRef(2L, "Mage", 700, 150, 10, "Commun"),
            0,
            1
        );
        final var mockHero3 = new Hero(
            3L,
            new HeroRef(3L, "Assassin", 880, 220, 5.5f, "Rare"),
            0,
            1
        );
        final var expectedHeroes = List.of(
            mockHero1,
            mockHero2,
            mockHero3
        );

        when(persistence.findPlayerById(eq(mockPlayer.getId()))).thenReturn(mockPlayer);
        when(persistence.findHeroPackById(eq(mockPack.getId()))).thenReturn(mockPack);

        final var actual = service.open(mockPlayer.getId(), mockPack.getId());
        assertThat(actual)
            .usingRecursiveComparison()
            .isEqualTo(expectedHeroes);

        //TODO: make it work
    }

}
