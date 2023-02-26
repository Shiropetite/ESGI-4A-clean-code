package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.FindPlayerByIdImpl;
import com.cleancode.adapter.out.entities.PlayerEntity;
import com.cleancode.adapter.out.mapper.PlayerMapper;
import com.cleancode.adapter.out.repositories.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class FindPlayerByIdTest {

    @InjectMocks
    private FindPlayerByIdImpl service;

    @Mock
    private PlayerRepository playerRepository;

    @Captor
    private ArgumentCaptor<Long> playerIdCaptor;

    @Test
    void should_find_player_by_id() {
        final var playerId = 1L;

        final var expectedPlayerEntity = new PlayerEntity();
        expectedPlayerEntity.setId(playerId);
        expectedPlayerEntity.setDeck(new ArrayList<>());

        final var expectedPlayer = PlayerMapper.get().toDomain(expectedPlayerEntity);

        when(playerRepository.findById(eq(playerId))).thenReturn(Optional.of(expectedPlayerEntity));

        final var actual = service.findPlayerById(playerId);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedPlayer);

        verify(playerRepository).findById(playerIdCaptor.capture());
        assertThat(playerIdCaptor.getValue()).isEqualTo(playerId);

        verifyNoMoreInteractions(playerRepository);
    }

}
