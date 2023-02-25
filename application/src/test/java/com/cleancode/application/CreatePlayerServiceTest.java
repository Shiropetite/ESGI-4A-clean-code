package com.cleancode.application;

import com.cleancode.application.ports.out.CreatePlayerPersistence;
import com.cleancode.application.services.CreatePlayerServiceImpl;
import com.cleancode.domain.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreatePlayerServiceTest {

    @InjectMocks
    private CreatePlayerServiceImpl service;

    @Mock
    private CreatePlayerPersistence persistence;

    @Captor
    private ArgumentCaptor<String> playerNameCaptor;
    @Captor
    private ArgumentCaptor<Player> playerCaptor;

    @Test
    void should_create_new_player() {
        final var mockPlayerName = "Toto";
        final var mockPlayer = Player.builder()
            .name(mockPlayerName)
            .build();
        final var expectedPlayer = Player.builder()
            .id(1L)
            .name(mockPlayerName)
            .build();

        when(persistence.findPlayerByName(eq(mockPlayerName))).thenReturn(null);
        when(persistence.createPlayer(any(Player.class))).thenReturn(expectedPlayer);

        final var actual = service.create(mockPlayerName);
        assertThat(actual)
            .usingRecursiveComparison()
            .isEqualTo(expectedPlayer);

        verify(persistence).findPlayerByName(playerNameCaptor.capture());
        assertThat(playerNameCaptor.getValue())
            .isEqualTo(mockPlayerName);

        verify(persistence).createPlayer(playerCaptor.capture());
        assertThat(playerCaptor.getValue())
            .usingRecursiveComparison()
            .isEqualTo(mockPlayer);

        verifyNoMoreInteractions(persistence);
    }

    @Test
    void should_not_create_new_player_when_already_exist_with_same_name() {
        final var mockPlayerName = "Toto";
        final var expectedPlayer = Player.builder()
            .id(1L)
            .name(mockPlayerName)
            .build();

        when(persistence.findPlayerByName(eq(mockPlayerName))).thenReturn(expectedPlayer);

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(
                () -> this.service.create(mockPlayerName)
            ).withMessage("Le joueur " + mockPlayerName + " existe déjà");

        verify(persistence).findPlayerByName(playerNameCaptor.capture());
        assertThat(playerNameCaptor.getValue())
            .isEqualTo(mockPlayerName);

        verifyNoMoreInteractions(persistence);
    }

}
