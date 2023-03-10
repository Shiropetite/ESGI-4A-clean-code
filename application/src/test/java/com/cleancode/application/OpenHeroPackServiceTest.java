package com.cleancode.application;

import com.cleancode.application.ports.out.OpenHeroPackPersistence;
import com.cleancode.application.services.OpenHeroPackServiceImpl;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroPack;
import com.cleancode.domain.HeroRef;
import com.cleancode.domain.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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
public final class OpenHeroPackServiceTest {

    @InjectMocks
    private OpenHeroPackServiceImpl service;

    @Mock
    private OpenHeroPackPersistence persistence;

    @Captor
    private ArgumentCaptor<Long> playerIdCaptor;

    @Captor
    private ArgumentCaptor<Long> heroPackIdCaptor;

    @Captor
    private ArgumentCaptor<Player> playerArgumentCaptor;

    @Test
    void should_open_hero_pack() {
        final var mockPlayer = Player.builder().id(1L).name("mockPlayer1").build();
        final var mockPack = new HeroPack(
            1L,
            "Argent",
            1,
            3,
            0.75f,
            0.2f,
            0.05f
        );
        final var heroRef = new HeroRef(null, "Tank", 1, 1, 1, "Commun");

        when(persistence.findPlayerById(eq(mockPlayer.getId()))).thenReturn(mockPlayer);
        when(persistence.findHeroPackById(eq(mockPack.getId()))).thenReturn(mockPack);
        when(persistence.findRandomHeroRefByRarity(any(String.class))).thenReturn(heroRef);
        when(persistence.createHero(any(Hero.class))).thenReturn(new Hero(heroRef));
        when(persistence.updatePlayer(any(Player.class))).thenReturn(null);

        final var actual = service.open(mockPlayer.getId(), mockPack.getId());
        assertThat(actual.size()).isEqualTo(mockPack.getNumberOfCards());
        // On ne peut pas comparer car les cartes sont tir?? al??atoirement

        verify(persistence).findPlayerById(playerIdCaptor.capture());
        assertThat(playerIdCaptor.getValue()).isEqualTo(mockPlayer.getId());

        verify(persistence).findHeroPackById(heroPackIdCaptor.capture());
        assertThat(heroPackIdCaptor.getValue()).isEqualTo(mockPack.getId());

        verify(persistence).updatePlayer(playerArgumentCaptor.capture());
        assertThat(playerArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(mockPlayer);

        verifyNoMoreInteractions(persistence);
    }

    @Test
    void should_throw_when_player_do_not_exist() {
        final var mockPlayerId = 1L;

        when(persistence.findPlayerById(eq(mockPlayerId))).thenReturn(null);
        when(persistence.findHeroPackById(any(Long.class))).thenReturn(null);

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(
                () -> this.service.open(mockPlayerId, 1L)
            ).withMessage("Le joueur " + mockPlayerId + " n'existe pas");

        verify(persistence).findPlayerById(playerIdCaptor.capture());
        assertThat(playerIdCaptor.getValue()).isEqualTo(mockPlayerId);

        verify(persistence).findHeroPackById(heroPackIdCaptor.capture());
        assertThat(heroPackIdCaptor.getValue()).isEqualTo(1L);

        verifyNoMoreInteractions(persistence);
    }

    @Test
    void should_throw_when_pack_do_not_exist() {
        final var mockPackId = 1L;

        when(persistence.findPlayerById(any(Long.class))).thenReturn(Player.builder().id(1L).build());
        when(persistence.findHeroPackById(eq(mockPackId))).thenReturn(null);

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(
                () -> this.service.open(1L, mockPackId)
            ).withMessage("Le pack " + mockPackId + " n'existe pas");

        verify(persistence).findPlayerById(playerIdCaptor.capture());
        assertThat(playerIdCaptor.getValue()).isEqualTo(1L);

        verify(persistence).findHeroPackById(heroPackIdCaptor.capture());
        assertThat(heroPackIdCaptor.getValue()).isEqualTo(mockPackId);

        verifyNoMoreInteractions(persistence);
    }

    @Test
    void should_throw_when_player_do_not_have_enough_tokens() {
        final var mockPlayer = Player.builder().id(1L).name("mockPlayer1").deck(List.of()).tokens(0).build();
        final var mockPack = new HeroPack(
            1L,
            "Argent",
            1,
            3,
            0.75f,
            0.2f,
            0.05f
        );

        when(persistence.findPlayerById(eq(mockPlayer.getId()))).thenReturn(mockPlayer);
        when(persistence.findHeroPackById(eq(mockPack.getId()))).thenReturn(mockPack);

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(
                () -> this.service.open(mockPlayer.getId(), mockPack.getId())
            ).withMessage("Le joueur " + mockPack.getId() + " n'a pas assez de tokens pour ouvrir le pack " + mockPack.getId());

        verify(persistence).findPlayerById(playerIdCaptor.capture());
        assertThat(playerIdCaptor.getValue()).isEqualTo(mockPlayer.getId());

        verify(persistence).findHeroPackById(heroPackIdCaptor.capture());
        assertThat(heroPackIdCaptor.getValue()).isEqualTo(mockPack.getId());

        verifyNoMoreInteractions(persistence);
    }

}
