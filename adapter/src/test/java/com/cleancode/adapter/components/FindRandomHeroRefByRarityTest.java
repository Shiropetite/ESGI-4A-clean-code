package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.FindRandomHeroRefByRarityImpl;
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

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class FindRandomHeroRefByRarityTest {

    @InjectMocks
    private FindRandomHeroRefByRarityImpl service;

    @Mock
    private HeroRefRepository heroRefRepository;

    @Captor
    private ArgumentCaptor<String> rarityCaptor;

    @Test
    void should_find_random_hero_ref_by_rarity() {
        final var rarity = "Commun";

        final var heroRefEntity = new HeroRefEntity();
        heroRefEntity.setRarity(rarity);

        final var expectedHeroRefEntities = new ArrayList<HeroRefEntity>();
        expectedHeroRefEntities.add(heroRefEntity);

        final var expectedHeroRef = HeroRefMapper.get().toDomain(expectedHeroRefEntities).get(0);

        when(heroRefRepository.findHeroRefEntityByRarity(eq(rarity))).thenReturn(Optional.of(expectedHeroRefEntities));

        final var actual = this.service.findRandomHeroRefByRarity(rarity);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroRef);

        verify(heroRefRepository).findHeroRefEntityByRarity(rarityCaptor.capture());
        assertThat(rarityCaptor.getValue()).isEqualTo(rarity);

        verifyNoMoreInteractions(heroRefRepository);
    }

}
