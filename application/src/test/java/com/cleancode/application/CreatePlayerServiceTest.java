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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreatePlayerServiceTest {

    @InjectMocks
    private CreatePlayerServiceImpl service;

    @Mock
    private CreatePlayerPersistence persistence;

    @Captor
    private ArgumentCaptor<Player> playerCaptor;

    @Test
    void should_create_new_player() {
        final var heroName = "Toto";
        final var player = Player.builder()
            .name(heroName)
            .build();
        final var expectedPlayer = Player.builder()
            .id(1L)
            .name(heroName)
            .build();

        when(persistence.create(any(Player.class))).thenReturn(expectedPlayer);

        final var actual = service.create(heroName);

        assertThat(actual)
            .usingRecursiveComparison()
            .isEqualTo(expectedPlayer);

        verify(persistence).create(playerCaptor.capture());

        assertThat(playerCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(player);

        verifyNoMoreInteractions(persistence);
    }

}
