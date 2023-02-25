package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.FindHeroDuelsByHeroImpl;
import com.cleancode.adapter.out.entities.HeroDuelEntity;
import com.cleancode.adapter.out.entities.HeroEntity;
import com.cleancode.adapter.out.entities.HeroRefEntity;
import com.cleancode.adapter.out.mapper.HeroDuelMapper;
import com.cleancode.adapter.out.mapper.HeroMapper;
import com.cleancode.adapter.out.repositories.HeroDuelRepository;
import com.cleancode.adapter.out.repositories.HeroRepository;
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
public class FindHeroDuelsByHeroTest {

    @InjectMocks
    private FindHeroDuelsByHeroImpl service;

    @Mock
    private HeroDuelRepository heroDuelRepository;

    @Mock
    private HeroRepository heroRepository;

    @Captor
    private ArgumentCaptor<Long> heroIdCaptor;

    @Captor
    private ArgumentCaptor<HeroEntity> heroEntityCaptor;

    @Test
    public void find_hero_duels_by_hero() {
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

        when(heroRepository.findById(eq(heroId))).thenReturn(Optional.of(heroEntity));
        when(heroDuelRepository.findHeroDuelEntitiesByWinner(eq(heroEntity))).thenReturn(Optional.of(expectedHeroDuelsEntity));
        when(heroDuelRepository.findHeroDuelEntitiesByLoser(eq(heroEntity))).thenReturn(Optional.of(new ArrayList<>()));

        final var actual = service.findHeroDuelsByHero(HeroMapper.get().toDomain(heroEntity));

        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroDuels);

        verify(heroRepository).findById(heroIdCaptor.capture());
        assertThat(heroIdCaptor.getValue()).isEqualTo(heroId);

        verify(heroDuelRepository).findHeroDuelEntitiesByWinner(heroEntityCaptor.capture());
        assertThat(heroEntityCaptor.getValue()).isEqualTo(heroEntity);

        verify(heroDuelRepository).findHeroDuelEntitiesByLoser(heroEntityCaptor.capture());
        assertThat(heroEntityCaptor.getValue()).isEqualTo(heroEntity);

        verifyNoMoreInteractions(heroRepository, heroDuelRepository);
    }

}
