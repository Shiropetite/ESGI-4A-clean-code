package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.UpdateHeroImpl;
import com.cleancode.adapter.out.entities.HeroEntity;
import com.cleancode.adapter.out.entities.HeroRefEntity;
import com.cleancode.adapter.out.mapper.HeroMapper;
import com.cleancode.adapter.out.repositories.HeroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateHeroTest {

    @InjectMocks
    private UpdateHeroImpl service;

    @Mock
    private HeroRepository heroRepository;

    @Captor
    private ArgumentCaptor<Long> heroIdCaptor;

    @Captor
    private ArgumentCaptor<HeroEntity> heroEntityCaptor;

    @Test
    public void update_hero_when_hero_id_found() {
        final var heroId = 1L;

        final var heroEntity = new HeroEntity();
        heroEntity.setId(heroId);
        heroEntity.setRef(new HeroRefEntity());

        final var expectedHero = HeroMapper.get().toDomain(heroEntity);

        when(heroRepository.findById(eq(heroId))).thenReturn(Optional.of(heroEntity));
        when(heroRepository.save(eq(heroEntity))).thenReturn(heroEntity);

        final var actual = this.service.updateHero(expectedHero);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHero);

        verify(heroRepository).findById(heroIdCaptor.capture());
        assertThat(heroIdCaptor.getValue()).isEqualTo(heroId);

        verify(heroRepository).save(heroEntityCaptor.capture());
        assertThat(heroEntityCaptor.getValue()).isEqualTo(heroEntity);

        verifyNoMoreInteractions(heroRepository);
    }

    @Test
    public void update_hero_return_null_when_hero_id_not_found() {
        final var heroEntity = new HeroEntity();
        heroEntity.setId(0L);
        heroEntity.setRef(new HeroRefEntity());

        final var expectedHero = HeroMapper.get().toDomain(heroEntity);

        when(heroRepository.findById(heroEntity.getId())).thenReturn(Optional.empty());

        final var actual = this.service.updateHero(expectedHero);

        assertThat(actual).usingRecursiveComparison().isEqualTo(null);

        verify(heroRepository).findById(heroIdCaptor.capture());
        assertThat(heroIdCaptor.getValue()).isEqualTo(heroEntity.getId());

        verifyNoMoreInteractions(heroRepository);
    }

}
