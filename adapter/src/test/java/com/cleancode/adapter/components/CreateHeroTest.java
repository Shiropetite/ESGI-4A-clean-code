package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.CreateHeroImpl;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class CreateHeroTest {

    @InjectMocks
    private CreateHeroImpl service;

    @Mock
    private HeroRepository heroRepository;

    @Captor
    private ArgumentCaptor<HeroEntity> heroEntityCaptor;

    @Test
    void should_create_hero() {
        final var heroRefEntity = new HeroRefEntity();
        heroRefEntity.setRarity("Commun");

        final var saveHeroEntity = new HeroEntity();
        saveHeroEntity.setId(1L);
        saveHeroEntity.setRef(heroRefEntity);

        final var expectedHero = HeroMapper.get().toDomain(saveHeroEntity);

        when(heroRepository.save(any(HeroEntity.class))).thenReturn(saveHeroEntity);

        final var actual = service.createHero(expectedHero);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHero);

        verify(heroRepository).save(heroEntityCaptor.capture());
        assertThat(heroEntityCaptor.getValue()).usingRecursiveComparison().isEqualTo(saveHeroEntity);

        verifyNoMoreInteractions(heroRepository);
    }

}
