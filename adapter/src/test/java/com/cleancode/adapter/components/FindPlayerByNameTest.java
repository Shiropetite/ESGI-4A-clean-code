package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.FindPlayerByNameImpl;
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
public class FindPlayerByNameTest {

    @InjectMocks
    private FindPlayerByNameImpl service;

    @Mock
    private PlayerRepository playerRepository;

    @Captor
    private ArgumentCaptor<String> playerNameCaptor;

    @Test
    public void find_player_by_name() {
        final var playerName = "Toto";

        final var expectedPlayerEntity = new PlayerEntity();
        expectedPlayerEntity.setName(playerName);
        expectedPlayerEntity.setDeck(new ArrayList<>());

        final var expectedPlayer = PlayerMapper.get().toDomain(expectedPlayerEntity);

        when(playerRepository.findPlayerEntityByName(eq(playerName))).thenReturn(Optional.of(expectedPlayerEntity));

        final var actual = service.findPlayerByName(playerName);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedPlayer);

        verify(playerRepository).findPlayerEntityByName(playerNameCaptor.capture());
        assertThat(playerNameCaptor.getValue()).isEqualTo(playerName);

        verifyNoMoreInteractions(playerRepository);
    }

}
