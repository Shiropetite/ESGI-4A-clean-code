package com.cleancode.adapter.services;

import com.cleancode.adapter.out.services.CreatePlayerPersistenceImpl;
import com.cleancode.application.ports.out.repositories.CreatePlayer;
import com.cleancode.application.ports.out.repositories.FindPlayerById;
import com.cleancode.application.ports.out.repositories.FindPlayerByName;
import com.cleancode.domain.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class CreatePlayerPersistenceTest {

    @InjectMocks
    private CreatePlayerPersistenceImpl persistence;

    @Mock
    private FindPlayerByName findPlayerByName;

    @Mock
    private CreatePlayer createPlayer;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Captor
    private ArgumentCaptor<Player> playerArgumentCaptor;

    @Test
    void should_find_player_by_name() {
        final var mockPlayerName = "mockPlayerName";
        final var expectedPlayer = Player.builder().name(mockPlayerName).build();

        when(findPlayerByName.findPlayerByName(mockPlayerName)).thenReturn(expectedPlayer);

        final var actual = persistence.findPlayerByName(mockPlayerName);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedPlayer);

        verify(findPlayerByName).findPlayerByName(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue()).isEqualTo(mockPlayerName);

        verifyNoMoreInteractions(findPlayerByName);
    }

    @Test
    void should_create_player() {
        final var expectedPlayer = Player.builder().build();

        when(createPlayer.createPlayer(eq(expectedPlayer))).thenReturn(expectedPlayer);

        final var actual = persistence.createPlayer(expectedPlayer);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedPlayer);

        verify(createPlayer).createPlayer(playerArgumentCaptor.capture());
        assertThat(playerArgumentCaptor.getValue()).isEqualTo(expectedPlayer);

        verifyNoMoreInteractions(createPlayer);
    }

}
