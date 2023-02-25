package com.cleancode.application;

import com.cleancode.application.ports.out.CreateHeroDuelPersistence;
import com.cleancode.application.services.CreateHeroDuelServiceImpl;
import com.cleancode.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateHeroDuelServiceTest {

    @InjectMocks
    private CreateHeroDuelServiceImpl service;

    @Mock
    private CreateHeroDuelPersistence persistence;

    @Test
    public void should_create_new_hero_duel_when_hero_1_win() {
        final var mockHero1 = new Hero(
            1L,
            new HeroRef(1L, "Tank", 1200, 120, 22, "Légendaire"),
            0,
            1
        );
        final var mockPlayer1 = Player.builder()
            .id(1L)
            .name("mockPlayer1")
            .deck(List.of(mockHero1))
            .build();
        final var mockHero2 = new Hero(
            4L,
            new HeroRef(2L, "Mage", 700, 150, 10, "Commun"),
            0,
            1
        );
        final var mockPlayer2 = Player.builder()
            .id(2L)
            .name("mockPlayer2")
            .deck(List.of(mockHero2))
            .build();
        final var expectedHeroDuel = new HeroDuel(
            1L,
            mockHero1,
            mockHero2
        );

        when(persistence.findPlayerById(eq(mockPlayer1.getId()))).thenReturn(mockPlayer1);
        when(persistence.findHeroById(eq(mockHero1.getId()))).thenReturn(mockHero1);
        when(persistence.findPlayerById(eq(mockPlayer2.getId()))).thenReturn(mockPlayer2);
        when(persistence.findHeroById(eq(mockHero2.getId()))).thenReturn(mockHero2);

        when(persistence.findHeroBonus(any(String.class), any(String.class))).thenReturn(null);
        when(persistence.findPlayerVictories(any(Player.class))).thenReturn(List.of());
        when(persistence.updateHero(any())).thenReturn(null);

        when(persistence.createHeroDuel(any(HeroDuel.class))).thenReturn(expectedHeroDuel);

        final var actual = service.create(
            mockPlayer1.getId(),
            mockHero1.getId(),
            mockPlayer2.getId(),
            mockHero2.getId()
        );
        assertThat(actual)
            .usingRecursiveComparison()
            .isEqualTo(expectedHeroDuel);


        verifyNoMoreInteractions(persistence);
    }

    @Test
    public void should_create_new_hero_duel_when_hero_2_win() {
        final var mockHero1 = new Hero(
                4L,
                new HeroRef(2L, "Mage", 700, 150, 10, "Commun"),
                0,
                1
        );
        final var mockPlayer1 = Player.builder()
                .id(1L)
                .name("mockPlayer1")
                .deck(List.of(mockHero1))
                .build();
        final var mockHero2 = new Hero(
                1L,
                new HeroRef(1L, "Tank", 1200, 120, 22, "Légendaire"),
                0,
                1
        );
        final var mockPlayer2 = Player.builder()
                .id(2L)
                .name("mockPlayer2")
                .deck(List.of(mockHero2))
                .build();
        final var expectedHeroDuel = new HeroDuel(
                1L,
                mockHero2,
                mockHero1
        );
        final var hero2Bonus = new HeroBonus(1L, "Tank", "Mage" , 1000);

        when(persistence.findPlayerById(eq(mockPlayer1.getId()))).thenReturn(mockPlayer1);
        when(persistence.findHeroById(eq(mockHero1.getId()))).thenReturn(mockHero1);
        when(persistence.findPlayerById(eq(mockPlayer2.getId()))).thenReturn(mockPlayer2);
        when(persistence.findHeroById(eq(mockHero2.getId()))).thenReturn(mockHero2);

        when(persistence.findHeroBonus(eq(mockHero2.getRef().getName()), eq(mockHero1.getRef().getName()))).thenReturn(hero2Bonus);
        when(persistence.findHeroBonus(eq(mockHero1.getRef().getName()), eq(mockHero2.getRef().getName()))).thenReturn(null);
        when(persistence.findPlayerVictories(any(Player.class))).thenReturn(List.of());
        when(persistence.updateHero(any())).thenReturn(null);

        when(persistence.createHeroDuel(any(HeroDuel.class))).thenReturn(expectedHeroDuel);

        final var actual = service.create(
                mockPlayer1.getId(),
                mockHero1.getId(),
                mockPlayer2.getId(),
                mockHero2.getId()
        );
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expectedHeroDuel);


        verifyNoMoreInteractions(persistence);
    }


    @Test
    public void should_add_token_when_win_5_time() {
        final var mockHero1 = new Hero(
                1L,
                new HeroRef(1L, "Tank", 1200, 120, 22, "Légendaire"),
                0,
                1
        );
        final var mockPlayer1 = Player.builder()
                .id(1L)
                .name("mockPlayer1")
                .deck(List.of(mockHero1))
                .build();
        final var mockHero2 = new Hero(
                4L,
                new HeroRef(2L, "Mage", 700, 150, 10, "Commun"),
                0,
                1
        );
        final var mockPlayer2 = Player.builder()
                .id(2L)
                .name("mockPlayer2")
                .deck(List.of(mockHero2))
                .build();
        final var expectedHeroDuel = new HeroDuel(
                1L,
                mockHero1,
                mockHero2
        );

        when(persistence.findPlayerById(eq(mockPlayer1.getId()))).thenReturn(mockPlayer1);
        when(persistence.findHeroById(eq(mockHero1.getId()))).thenReturn(mockHero1);
        when(persistence.findPlayerById(eq(mockPlayer2.getId()))).thenReturn(mockPlayer2);
        when(persistence.findHeroById(eq(mockHero2.getId()))).thenReturn(mockHero2);

        when(persistence.findHeroBonus(any(String.class), any(String.class))).thenReturn(null);
        when(persistence.findPlayerVictories(any(Player.class))).thenReturn(List.of(
                expectedHeroDuel,
                expectedHeroDuel,
                expectedHeroDuel,
                expectedHeroDuel
        ));
        when(persistence.updateHero(any())).thenReturn(null);
        when(persistence.updatePlayer(any())).thenReturn(null);

        when(persistence.createHeroDuel(any(HeroDuel.class))).thenReturn(expectedHeroDuel);

        final var actual = service.create(
                mockPlayer1.getId(),
                mockHero1.getId(),
                mockPlayer2.getId(),
                mockHero2.getId()
        );

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expectedHeroDuel);

        verifyNoMoreInteractions(persistence);
    }

    @Test
    public void should_throw_when_player_1_not_found() {
        final var mockPlayer1Id = 1L;

        when(persistence.findPlayerById(any())).thenReturn(null);
        when(persistence.findHeroById(any())).thenReturn(null);

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(
                () -> this.service.create(mockPlayer1Id, 1L, 2L, 4L)
            ).withMessage("Le joueur " + mockPlayer1Id + " n'existe pas");

        verifyNoMoreInteractions(persistence);
    }

    @Test
    public void should_throw_when_player_2_not_found() {
        final var mockPlayer = Player.builder().id(1L).build();
        final var mockPlayer2Id = 2L;

        when(persistence.findPlayerById(eq(mockPlayer.getId()))).thenReturn(mockPlayer);
        when(persistence.findPlayerById(eq(mockPlayer2Id))).thenReturn(null);
        when(persistence.findHeroById(any())).thenReturn(null);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(
                        () -> this.service.create(mockPlayer.getId(), 1L, mockPlayer2Id, 4L)
                ).withMessage("Le joueur " + mockPlayer2Id + " n'existe pas");

        verifyNoMoreInteractions(persistence);
    }

    @Test
    public void should_throw_when_players_are_the_same() {
        final var mockPlayer = Player.builder()
            .id(1L)
            .name("mockPlayer")
            .build();

        when(persistence.findPlayerById(eq(mockPlayer.getId()))).thenReturn(mockPlayer);
        when(persistence.findHeroById(any())).thenReturn(null);

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(
                () -> this.service.create(mockPlayer.getId(), 1L, mockPlayer.getId(), 4L)
            ).withMessage("Un joueur ne peut pas s'affronter lui-même");

        verifyNoMoreInteractions(persistence);
    }

    @Test
    public void should_throw_when_hero_1_not_found() {
        final var mockHeroId = 1L;
        final var mockPlayer1 = Player.builder()
            .id(1L)
            .name("mockPlayer1")
            .build();
        final var mockPlayer2 = Player.builder()
            .id(2L)
            .name("mockPlayer2")
            .build();

        when(persistence.findPlayerById(eq(mockPlayer1.getId()))).thenReturn(mockPlayer1);
        when(persistence.findHeroById(any())).thenReturn(null);
        when(persistence.findPlayerById(eq(mockPlayer2.getId()))).thenReturn(mockPlayer2);

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(
                () -> this.service.create(1L, 1L, 2L, 4L)
            ).withMessage("Le héros " + mockHeroId + " n'existe pas");

        verifyNoMoreInteractions(persistence);
    }

    @Test
    public void should_throw_when_hero_2_not_found() {
        final var mockHero2Id = 2L;
        final var mockHero1 = new Hero(
            1L,
            new HeroRef(null, "Tank", 1,1,1, "Commun"),
            0,
            1
        );
        final var mockPlayer1 = Player.builder()
                .id(1L)
                .name("mockPlayer1")
                .build();
        final var mockPlayer2 = Player.builder()
                .id(2L)
                .name("mockPlayer2")
                .build();

        when(persistence.findPlayerById(eq(mockPlayer1.getId()))).thenReturn(mockPlayer1);
        when(persistence.findHeroById(eq(mockHero1.getId()))).thenReturn(mockHero1);
        when(persistence.findPlayerById(eq(mockPlayer2.getId()))).thenReturn(mockPlayer2);
        when(persistence.findHeroById(eq(mockHero2Id))).thenReturn(null);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(
                        () -> this.service.create(mockPlayer1.getId(), mockHero1.getId(), mockPlayer2.getId(), mockHero2Id)
                ).withMessage("Le héros " + mockHero2Id + " n'existe pas");

        verifyNoMoreInteractions(persistence);
    }

    @Test
    public void should_throw_when_hero_1_not_found_in_player_1_deck() {
        final var mockHero1 = new Hero(
            1L,
            new HeroRef(1L, "Tank", 1200, 120, 22, "Légendaire"),
            0,
            1
        );
        final var mockPlayer1 = Player.builder()
            .id(1L)
            .name("mockPlayer1")
            .build();
        final var mockPlayer2 = Player.builder()
            .id(2L)
            .name("mockPlayer2")
            .build();

        when(persistence.findPlayerById(eq(mockPlayer1.getId()))).thenReturn(mockPlayer1);
        when(persistence.findHeroById(any())).thenReturn(mockHero1);
        when(persistence.findPlayerById(eq(mockPlayer2.getId()))).thenReturn(mockPlayer2);

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(
                () -> this.service.create(mockPlayer1.getId(), mockHero1.getId(), mockPlayer2.getId(), 4L)
            ).withMessage("Le joueur " + mockPlayer1.getId() + " ne possède pas le héros " + mockHero1.getId());

        verifyNoMoreInteractions(persistence);
    }

    @Test
    public void should_throw_when_hero_2_not_found_in_player_2_deck() {
        final var mockHero1 = new Hero(
                1L,
                new HeroRef(1L, "Tank", 1200, 120, 22, "Légendaire"),
                0,
                1
        );
        final var mockHero2 = new Hero(
                2L,
                new HeroRef(1L, "Tank", 1200, 120, 22, "Légendaire"),
                0,
                1
        );
        final var mockPlayer1 = Player.builder()
                .id(1L)
                .name("mockPlayer1")
                .deck(List.of(mockHero1))
                .build();
        final var mockPlayer2 = Player.builder()
                .id(2L)
                .name("mockPlayer2")
                .build();

        when(persistence.findPlayerById(eq(mockPlayer1.getId()))).thenReturn(mockPlayer1);
        when(persistence.findHeroById(eq(mockHero1.getId()))).thenReturn(mockHero1);
        when(persistence.findPlayerById(eq(mockPlayer2.getId()))).thenReturn(mockPlayer2);
        when(persistence.findHeroById(eq(mockHero2.getId()))).thenReturn(mockHero2);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(
                        () -> this.service.create(mockPlayer1.getId(), mockHero1.getId(), mockPlayer2.getId(), mockPlayer2.getId())
                ).withMessage("Le joueur " + mockPlayer2.getId() + " ne possède pas le héros " + mockHero2.getId());

        verifyNoMoreInteractions(persistence);
    }

    @Test
    public void should_throw_when_heroes_not_able_to_duel() {
        final var mockHero1 = new Hero(
            1L,
            new HeroRef(1L, "Tank", 1200, 120, 22, "Légendaire"),
            0,
            3
        );
        final var mockPlayer1 = Player.builder()
            .id(1L)
            .name("mockPlayer1")
            .deck(List.of(mockHero1))
            .build();
        final var mockHero2 = new Hero(
            4L,
            new HeroRef(2L, "Mage", 700, 150, 10, "Commun"),
            0,
            2
        );
        final var mockPlayer2 = Player.builder()
            .id(2L)
            .name("mockPlayer2")
            .deck(List.of(mockHero2))
            .build();

        when(persistence.findPlayerById(eq(mockPlayer1.getId()))).thenReturn(mockPlayer1);
        when(persistence.findHeroById(eq(mockHero1.getId()))).thenReturn(mockHero1);
        when(persistence.findPlayerById(eq(mockPlayer2.getId()))).thenReturn(mockPlayer2);
        when(persistence.findHeroById(eq(mockHero2.getId()))).thenReturn(mockHero2);

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(
                () -> this.service.create(mockPlayer1.getId(), mockHero1.getId(), mockPlayer2.getId(), mockHero2.getId())
            ).withMessage("Le héros " + mockHero1.getId() + " ne peut pas affronter le héros " + mockHero2.getId());

        verifyNoMoreInteractions(persistence);
    }

}
