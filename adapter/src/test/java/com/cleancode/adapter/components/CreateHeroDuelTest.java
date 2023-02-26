package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.CreateHeroDuelImpl;
import com.cleancode.adapter.out.entities.HeroDuelEntity;
import com.cleancode.adapter.out.entities.HeroEntity;
import com.cleancode.adapter.out.entities.HeroRefEntity;
import com.cleancode.adapter.out.mapper.HeroMapper;
import com.cleancode.adapter.out.repositories.HeroDuelRepository;
import com.cleancode.adapter.out.repositories.HeroRepository;
import com.cleancode.domain.HeroDuel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class CreateHeroDuelTest {

    @InjectMocks
    private CreateHeroDuelImpl service;

    @Mock
    private HeroRepository heroRepository;

    @Mock
    private HeroDuelRepository heroDuelRepository;

    @Captor
    private ArgumentCaptor<Long> findByIdCaptor;

    @Captor
    private ArgumentCaptor<HeroDuelEntity> heroDuelCaptor;

    @Test
    void should_create_hero_duel() {
        final var heroRefEntity = new HeroRefEntity();
        heroRefEntity.setId(1L);

        final var hero1Entity = new HeroEntity();
        hero1Entity.setId(1L);
        hero1Entity.setRef(heroRefEntity);

        final var hero2Entity = new HeroEntity();
        hero2Entity.setId(2L);
        hero2Entity.setRef(heroRefEntity);

        final var heroDuelEntity= new HeroDuelEntity();
        heroDuelEntity.setWinner(hero1Entity);
        heroDuelEntity.setLoser(hero2Entity);

        final var heroDuelEntitySaved = new HeroDuelEntity();
        heroDuelEntitySaved.setId(1L);
        heroDuelEntitySaved.setWinner(hero1Entity);
        heroDuelEntitySaved.setLoser(hero2Entity);

        final var hero1 = HeroMapper.get().toDomain(hero1Entity);
        final var hero2 = HeroMapper.get().toDomain(hero2Entity);
        final var expectedHeroDuel = new HeroDuel(1L, hero1, hero2);

        when(heroRepository.findById(eq(1L))).thenReturn(Optional.of(hero1Entity));
        when(heroRepository.findById(eq(2L))).thenReturn(Optional.of(hero2Entity));
        when(heroDuelRepository.save(any(HeroDuelEntity.class))).thenReturn(heroDuelEntitySaved);

        final var actual = service.createHeroDuel(expectedHeroDuel);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroDuel);

        verify(heroRepository, times(2)).findById(findByIdCaptor.capture());
        assertThat(findByIdCaptor.getAllValues().get(0)).isEqualTo(expectedHeroDuel.getWinner().getId());
        assertThat(findByIdCaptor.getAllValues().get(1)).isEqualTo(expectedHeroDuel.getLoser().getId());

        verify(heroDuelRepository).save(heroDuelCaptor.capture());
        assertThat(heroDuelCaptor.getValue()).isEqualTo(heroDuelEntity);

        verifyNoMoreInteractions(heroRepository, heroDuelRepository);
    }

    @Test
    void should_return_null_when_winner_not_found() {
        final var heroRefEntity = new HeroRefEntity();
        heroRefEntity.setId(1L);

        final var hero1Entity = new HeroEntity();
        hero1Entity.setId(1L);
        hero1Entity.setRef(heroRefEntity);

        final var hero2Entity = new HeroEntity();
        hero2Entity.setId(2L);
        hero2Entity.setRef(heroRefEntity);

        final var hero1 = HeroMapper.get().toDomain(hero1Entity);
        final var hero2 = HeroMapper.get().toDomain(hero2Entity);
        final var mockHeroDuel = new HeroDuel(1L, hero1, hero2);

        when(heroRepository.findById(hero1Entity.getId())).thenReturn(Optional.empty());
        when(heroRepository.findById(hero2Entity.getId())).thenReturn(Optional.of(hero2Entity));

        final var actual = service.createHeroDuel(mockHeroDuel);

        assertThat(actual).usingRecursiveComparison().isEqualTo(null);

        verify(heroRepository, times(2)).findById(findByIdCaptor.capture());
        assertThat(findByIdCaptor.getAllValues().get(0)).isEqualTo(mockHeroDuel.getWinner().getId());
        assertThat(findByIdCaptor.getAllValues().get(1)).isEqualTo(mockHeroDuel.getLoser().getId());

        verifyNoMoreInteractions(heroRepository, heroDuelRepository);
    }

    @Test
    void should_return_null_when_loser_not_found() {
        final var heroRefEntity = new HeroRefEntity();
        heroRefEntity.setId(1L);

        final var hero1Entity = new HeroEntity();
        hero1Entity.setRef(heroRefEntity);

        final var hero2Entity = new HeroEntity();
        hero2Entity.setId(2L);
        hero2Entity.setRef(heroRefEntity);

        final var hero1 = HeroMapper.get().toDomain(hero1Entity);
        final var hero2 = HeroMapper.get().toDomain(hero2Entity);
        final var mockHeroDuel = new HeroDuel(1L, hero1, hero2);

        when(heroRepository.findById(hero1Entity.getId())).thenReturn(Optional.of(hero1Entity));
        when(heroRepository.findById(hero2Entity.getId())).thenReturn(Optional.empty());

        final var actual = service.createHeroDuel(mockHeroDuel);

        assertThat(actual).usingRecursiveComparison().isEqualTo(null);

        verify(heroRepository, times(2)).findById(findByIdCaptor.capture());
        assertThat(findByIdCaptor.getAllValues().get(0)).isEqualTo(mockHeroDuel.getWinner().getId());
        assertThat(findByIdCaptor.getAllValues().get(1)).isEqualTo(mockHeroDuel.getLoser().getId());

        verifyNoMoreInteractions(heroRepository, heroDuelRepository);
    }

}
