package com.cleancode.application;

import com.cleancode.application.ports.out.SearchPlayerPersistence;
import com.cleancode.application.services.SearchPlayerServiceImpl;
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
public class SearchPlayerServiceTest {

    @InjectMocks
    private SearchPlayerServiceImpl service;

    @Mock
    private SearchPlayerPersistence persistence;

    @Captor
    private ArgumentCaptor<String> playerNameCaptor;

    @Test
    public void should_search_player() {
        final var expectedPlayer = Player.builder()
            .id(1L)
            .name("expectedPlayer")
            .build();

        when(persistence.findPlayerByName(expectedPlayer.getName())).thenReturn(expectedPlayer);

        final var actual = service.search(expectedPlayer.getName());
        assertThat(actual)
            .usingRecursiveComparison()
            .isEqualTo(expectedPlayer);

        verify(persistence).findPlayerByName(playerNameCaptor.capture());
        assertThat(playerNameCaptor.getValue())
            .isEqualTo(expectedPlayer.getName());

        verifyNoMoreInteractions(persistence);
    }

    @Test
    public void should_throw_when_player_not_found() {
        final var expectedPlayer = Player.builder()
            .id(1L)
            .name("expectedPlayer")
            .build();

        when(persistence.findPlayerByName(expectedPlayer.getName())).thenReturn(null);

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(
                () -> this.service.search(expectedPlayer.getName())
            ).withMessage("Le joueur " + expectedPlayer.getName() + " n'existe pas");

        verify(persistence).findPlayerByName(playerNameCaptor.capture());
        assertThat(playerNameCaptor.getValue())
            .isEqualTo(expectedPlayer.getName());

        verifyNoMoreInteractions(persistence);
    }

}
