package com.cleancode.adapter.services;

import com.cleancode.adapter.out.services.CreateHeroDuelPersistenceImpl;
import com.cleancode.application.ports.out.repositories.*;
import com.cleancode.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class CreateHeroDuelPersistenceTest {

    @InjectMocks
    private CreateHeroDuelPersistenceImpl persistence;

    @Mock
    private FindPlayerById findPlayerById;

    @Mock
    private FindHeroById findHeroById;

    @Mock
    private FindPlayerVictories findPlayerVictories;

    @Mock
    private FindHeroBonus findHeroBonus;

    @Mock
    private UpdatePlayer updatePlayer;

    @Mock
    private UpdateHero updateHero;

    @Mock
    private CreateHeroDuel createHeroDuel;

    @Captor
    private ArgumentCaptor<Long> longArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Captor
    private ArgumentCaptor<Player> playerArgumentCaptor;

    @Captor
    private ArgumentCaptor<Hero> heroArgumentCaptor;

    @Captor
    private ArgumentCaptor<HeroDuel> heroDuelArgumentCaptor;

    @Test
    void should_find_player_by_id() {
        final var mockPlayerId = 1L;
        final var expectedPlayer = Player.builder().id(mockPlayerId).build();

        when(findPlayerById.findPlayerById(mockPlayerId)).thenReturn(expectedPlayer);

        final var actual = persistence.findPlayerById(mockPlayerId);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedPlayer);

        verify(findPlayerById).findPlayerById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(mockPlayerId);

        verifyNoMoreInteractions(findPlayerById);
    }

    @Test
    void should_find_hero_by_id() {
        final var mockHeroId = 1L;
        final var expectedHero = new Hero(mockHeroId, null, 0, 0);

        when(findHeroById.findHeroById(mockHeroId)).thenReturn(expectedHero);

        final var actual = persistence.findHeroById(mockHeroId);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHero);

        verify(findHeroById).findHeroById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(mockHeroId);

        verifyNoMoreInteractions(findHeroById);
    }

    @Test
    void should_find_player_victories() {
        final var mockPlayer = Player.builder().build();
        final var expectedHeroDuels = List.of(
            new HeroDuel(1L, null, null)
        );

        when(findPlayerVictories.findPlayerVictories(eq(mockPlayer))).thenReturn(expectedHeroDuels);

        final var actual = persistence.findPlayerVictories(mockPlayer);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroDuels);

        verify(findPlayerVictories).findPlayerVictories(playerArgumentCaptor.capture());
        assertThat(playerArgumentCaptor.getValue()).isEqualTo(mockPlayer);

        verifyNoMoreInteractions(findPlayerVictories);
    }

    @Test
    void should_find_hero_bonus() {
        final var mockHeroName = "mockHeroName";
        final var expectedHeroBonus = new HeroBonus(1L, mockHeroName, mockHeroName,0);

        when(findHeroBonus.findHeroBonus(eq(mockHeroName), eq(mockHeroName))).thenReturn(expectedHeroBonus);

        final var actual = persistence.findHeroBonus(mockHeroName, mockHeroName);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroBonus);

        verify(findHeroBonus).findHeroBonus(stringArgumentCaptor.capture(), stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue()).isEqualTo(mockHeroName);

        verifyNoMoreInteractions(findHeroBonus);
    }

    @Test
    void should_update_player() {
        final var mockPlayer= Player.builder().build();

        when(updatePlayer.updatePlayer(eq(mockPlayer))).thenReturn(mockPlayer);

        final var actual = persistence.updatePlayer(mockPlayer);
        assertThat(actual).usingRecursiveComparison().isEqualTo(mockPlayer);

        verify(updatePlayer).updatePlayer(playerArgumentCaptor.capture());
        assertThat(playerArgumentCaptor.getValue()).isEqualTo(mockPlayer);

        verifyNoMoreInteractions(updatePlayer);
    }

    @Test
    void should_update_hero() {
        final var mockHero = new Hero(1L, null, 0, 0);

        when(updateHero.updateHero(eq(mockHero))).thenReturn(mockHero);

        final var actual = persistence.updateHero(mockHero);
        assertThat(actual).usingRecursiveComparison().isEqualTo(mockHero);

        verify(updateHero).updateHero(heroArgumentCaptor.capture());
        assertThat(heroArgumentCaptor.getValue()).isEqualTo(mockHero);

        verifyNoMoreInteractions(updateHero);
    }

    @Test
    void should_create_hero_duel() {
        final var expectedHeroDuel = new HeroDuel(1L, null, null);

        when(createHeroDuel.createHeroDuel(eq(expectedHeroDuel))).thenReturn(expectedHeroDuel);

        final var actual = persistence.createHeroDuel(expectedHeroDuel);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroDuel);

        verify(createHeroDuel).createHeroDuel(heroDuelArgumentCaptor.capture());
        assertThat(heroDuelArgumentCaptor.getValue()).isEqualTo(expectedHeroDuel);

        verifyNoMoreInteractions(createHeroDuel);
    }

}
