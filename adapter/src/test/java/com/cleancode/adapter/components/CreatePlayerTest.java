package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.CreatePlayerImpl;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class CreatePlayerTest {

    @InjectMocks
    private CreatePlayerImpl service;

    @Mock
    private PlayerRepository playerRepository;

    @Captor
    private ArgumentCaptor<PlayerEntity> playerEntityCaptor;

    @Test
    void should_create_player() {
        final var playerEntity = new PlayerEntity();
        playerEntity.setName("Toto");
        playerEntity.setDeck(new ArrayList<>());

        final var playerEntitySave = new PlayerEntity();
        playerEntitySave.setId(1L);
        playerEntitySave.setName("Toto");
        playerEntitySave.setDeck(new ArrayList<>());

        final var expectedPlayer = PlayerMapper.get().toDomain(playerEntitySave);

        when(playerRepository.save(any(PlayerEntity.class))).thenReturn(playerEntitySave);

        final var actual = service.createPlayer(expectedPlayer);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedPlayer);

        verify(playerRepository).save(playerEntityCaptor.capture());
        assertThat(playerEntityCaptor.getValue()).usingRecursiveComparison().isEqualTo(playerEntity);

        verifyNoMoreInteractions(playerRepository);
    }

}
