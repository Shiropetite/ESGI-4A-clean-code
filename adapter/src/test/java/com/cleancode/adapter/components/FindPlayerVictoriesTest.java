package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.FindPlayerVictoriesImpl;
import com.cleancode.adapter.out.entities.HeroDuelEntity;
import com.cleancode.adapter.out.entities.HeroEntity;
import com.cleancode.adapter.out.entities.HeroRefEntity;
import com.cleancode.adapter.out.mapper.HeroDuelMapper;
import com.cleancode.adapter.out.mapper.HeroMapper;
import com.cleancode.adapter.out.repositories.HeroDuelRepository;
import com.cleancode.adapter.out.repositories.HeroRepository;
import com.cleancode.domain.Hero;
import com.cleancode.domain.Player;
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
public final class FindPlayerVictoriesTest {

    @InjectMocks
    private FindPlayerVictoriesImpl service;

    @Mock
    private HeroRepository heroRepository;

    @Mock
    private HeroDuelRepository heroDuelRepository;

    @Captor
    private ArgumentCaptor<Long> heroIdCaptor;

    @Captor
    private ArgumentCaptor<HeroEntity> heroEntityCaptor;

    @Test
    void should_find_player_victories() {
        final var heroId = 1L;

        final var heroEntity = new HeroEntity();
        heroEntity.setId(heroId);
        heroEntity.setRef(new HeroRefEntity());

        final var heroDuelEntity = new HeroDuelEntity();
        heroDuelEntity.setWinner(heroEntity);
        heroDuelEntity.setLoser(heroEntity);

        final var expectedHeroDuelsEntity = new ArrayList<HeroDuelEntity>();
        expectedHeroDuelsEntity.add(heroDuelEntity);

        final var expectedHeroDuels = HeroDuelMapper.get().toDomain(expectedHeroDuelsEntity);

        final var deck = new ArrayList<Hero>();
        deck.add(HeroMapper.get().toDomain(heroEntity));

        final var player = Player.builder().deck(deck).build();

        when(heroRepository.findById(eq(heroId))).thenReturn(Optional.of(heroEntity));
        when(heroDuelRepository.findHeroDuelEntitiesByWinner(eq(heroEntity))).thenReturn(Optional.of(expectedHeroDuelsEntity));

        final var actual = service.findPlayerVictories(player);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroDuels);

        verify(heroRepository).findById(heroIdCaptor.capture());
        assertThat(heroIdCaptor.getValue()).isEqualTo(heroId);

        verify(heroDuelRepository).findHeroDuelEntitiesByWinner(heroEntityCaptor.capture());
        assertThat(heroEntityCaptor.getValue()).isEqualTo(heroEntity);

        verifyNoMoreInteractions(heroRepository);
    }

}
