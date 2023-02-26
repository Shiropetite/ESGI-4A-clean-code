package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.UpdatePlayerImpl;
import com.cleancode.adapter.out.entities.PlayerEntity;
import com.cleancode.adapter.out.mapper.PlayerMapper;
import com.cleancode.adapter.out.repositories.PlayerRepository;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroRef;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class UpdatePlayerTest {

    @InjectMocks
    private UpdatePlayerImpl service;

    @Mock
    private PlayerRepository playerRepository;

    @Captor
    private ArgumentCaptor<Long> playerIdCaptor;

    @Captor
    private ArgumentCaptor<PlayerEntity> playerEntityCaptor;

    @Test
    void should_update_player_with_no_new_heroes() {
        final var mockPlayerId = 1L;

        final var mockPlayerEntity = new PlayerEntity();
        mockPlayerEntity.setId(mockPlayerId);
        mockPlayerEntity.setDeck(new ArrayList<>());

        final var mockPlayer = PlayerMapper.get().toDomain(mockPlayerEntity);

        when(playerRepository.findById(eq(mockPlayerId))).thenReturn(Optional.of(mockPlayerEntity));
        when(playerRepository.save(eq(mockPlayerEntity))).thenReturn(mockPlayerEntity);

        final var actual = this.service.updatePlayer(mockPlayer);
        assertThat(actual).usingRecursiveComparison().isEqualTo(mockPlayer);

        verify(playerRepository).findById(playerIdCaptor.capture());
        assertThat(playerIdCaptor.getValue()).isEqualTo(mockPlayerId);

        verify(playerRepository).save(playerEntityCaptor.capture());
        assertThat(playerEntityCaptor.getValue()).isEqualTo(mockPlayerEntity);

        verifyNoMoreInteractions(playerRepository);
    }

    @Test
    void should_update_player_with_new_heroes() {
        final var mockPlayerId = 1L;

        final var mockPlayerEntity = new PlayerEntity();
        mockPlayerEntity.setId(mockPlayerId);
        mockPlayerEntity.setDeck(new ArrayList<>());

        final var mockPlayer = PlayerMapper.get().toDomain(mockPlayerEntity);
        mockPlayer.getDeck().addAll(List.of(
            new Hero(
                1L,
                new HeroRef(1L, "Tank", 1200, 120, 22, "Légendaire"),
                0,
                1
            )
        ));

        when(playerRepository.findById(mockPlayerEntity.getId())).thenReturn(Optional.empty());

        final var actual = this.service.updatePlayer(mockPlayer);
        assertThat(actual).usingRecursiveComparison().isEqualTo(null);

        verify(playerRepository).findById(playerIdCaptor.capture());
        assertThat(playerIdCaptor.getValue()).isEqualTo(mockPlayerEntity.getId());

        verifyNoMoreInteractions(playerRepository);
    }

    @Test
    void should_return_null_when_player_not_found() {
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
