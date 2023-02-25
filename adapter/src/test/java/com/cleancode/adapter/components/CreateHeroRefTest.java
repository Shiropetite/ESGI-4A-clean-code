package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.CreateHeroRefImpl;
import com.cleancode.adapter.out.entities.HeroRefEntity;
import com.cleancode.adapter.out.mapper.HeroRefMapper;
import com.cleancode.adapter.out.repositories.HeroRefRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateHeroRefTest {

    @InjectMocks
    private CreateHeroRefImpl service;

    @Mock
    private HeroRefRepository heroRefRepository;

    @Captor
    private ArgumentCaptor<HeroRefEntity> heroRefEntityCaptor;

    @Test
    public void create_hero_ref() {
        final var heroRefEntitySave = new HeroRefEntity();
        heroRefEntitySave.setId(1L);
        heroRefEntitySave.setRarity("Commun");

        final var expectedHeroRef = HeroRefMapper.get().toDomain(heroRefEntitySave);

        when(heroRefRepository.save(any(HeroRefEntity.class))).thenReturn(heroRefEntitySave);

        final var actual = service.createHeroRef(expectedHeroRef);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroRef);

        verify(heroRefRepository).save(heroRefEntityCaptor.capture());
        assertThat(heroRefEntityCaptor.getValue()).usingRecursiveComparison().isEqualTo(heroRefEntitySave);

        verifyNoMoreInteractions(heroRefRepository);
    }

}
