package com.cleancode.adapter.services;

import com.cleancode.adapter.out.services.OpenHeroPackPersistenceImpl;
import com.cleancode.application.ports.out.repositories.*;
import com.cleancode.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class OpenHeroPackPersistenceTest {

    @InjectMocks
    private OpenHeroPackPersistenceImpl persistence;

    @Mock
    private FindPlayerById findPlayerById;

    @Mock
    private FindHeroPackById findHeroPackById;

    @Mock
    private FindRandomHeroRefByRarity findRandomHeroRefByRarity;

    @Mock
    private CreateHero createHero;

    @Mock
    private UpdatePlayer updatePlayer;

    @Captor
    private ArgumentCaptor<Long> longArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Captor
    private ArgumentCaptor<Hero> heroArgumentCaptor;

    @Captor
    private ArgumentCaptor<Player> playerArgumentCaptor;

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
    void should_find_hero_pack_by_id() {
        final var mockHeroPackId = 1L;
        final var expectedHeroPack = new HeroPack(1L, "", 0, 0, 0, 0, 0);

        when(findHeroPackById.findHeroPackById(mockHeroPackId)).thenReturn(expectedHeroPack);

        final var actual = persistence.findHeroPackById(mockHeroPackId);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroPack);

        verify(findHeroPackById).findHeroPackById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(mockHeroPackId);

        verifyNoMoreInteractions(findHeroPackById);
    }

    @Test
    void should_find_random_hero_ref_by_rarity() {
        final var mockHeroRarityName = "mockHeroRarityName";
        final var expectedHeroRef = new HeroRef(1L, "", 0, 0, 0, "");

        when(findRandomHeroRefByRarity.findRandomHeroRefByRarity(eq(mockHeroRarityName))).thenReturn(expectedHeroRef);

        final var actual = persistence.findRandomHeroRefByRarity(mockHeroRarityName);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroRef);

        verify(findRandomHeroRefByRarity).findRandomHeroRefByRarity(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue()).isEqualTo(mockHeroRarityName);

        verifyNoMoreInteractions(findRandomHeroRefByRarity);
    }

    @Test
    void should_create_hero() {
        final var expectedHero = new Hero(1L, null, 0, 0);

        when(createHero.createHero(eq(expectedHero))).thenReturn(expectedHero);

        final var actual = persistence.createHero(expectedHero);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHero);

        verify(createHero).createHero(heroArgumentCaptor.capture());
        assertThat(heroArgumentCaptor.getValue()).isEqualTo(expectedHero);

        verifyNoMoreInteractions(createHero);
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

}
