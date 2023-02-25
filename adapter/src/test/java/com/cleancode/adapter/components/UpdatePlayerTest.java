package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.UpdatePlayerImpl;
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
public class UpdatePlayerTest {

    @InjectMocks
    private UpdatePlayerImpl service;

    @Mock
    private PlayerRepository playerRepository;

    @Captor
    private ArgumentCaptor<Long> playerIdCaptor;

    @Captor
    private ArgumentCaptor<PlayerEntity> playerEntityCaptor;

    @Test
    public void update_player_when_player_id_found() {
        final var playerId = 1L;

        final var playerEntity = new PlayerEntity();
        playerEntity.setId(playerId);
        playerEntity.setDeck(new ArrayList<>());

        final var expectedPlayer = PlayerMapper.get().toDomain(playerEntity);

        when(playerRepository.findById(eq(playerId))).thenReturn(Optional.of(playerEntity));
        when(playerRepository.save(eq(playerEntity))).thenReturn(playerEntity);

        final var actual = this.service.updatePlayer(expectedPlayer);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedPlayer);

        verify(playerRepository).findById(playerIdCaptor.capture());
        assertThat(playerIdCaptor.getValue()).isEqualTo(playerId);

        verify(playerRepository).save(playerEntityCaptor.capture());
        assertThat(playerEntityCaptor.getValue()).isEqualTo(playerEntity);

        verifyNoMoreInteractions(playerRepository);
    }

    @Test
    public void update_player_return_null_when_player_id_not_found() {
        final var playerEntity = new PlayerEntity();
        playerEntity.setId(0L);
        playerEntity.setDeck(new ArrayList<>());

        final var expectedPlayer = PlayerMapper.get().toDomain(playerEntity);

        when(playerRepository.findById(playerEntity.getId())).thenReturn(Optional.empty());

        final var actual = this.service.updatePlayer(expectedPlayer);

        assertThat(actual).usingRecursiveComparison().isEqualTo(null);

        verify(playerRepository).findById(playerIdCaptor.capture());
        assertThat(playerIdCaptor.getValue()).isEqualTo(playerEntity.getId());

        verifyNoMoreInteractions(playerRepository);
    }

}
