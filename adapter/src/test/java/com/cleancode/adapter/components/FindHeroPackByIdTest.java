package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.FindHeroPackByIdImpl;
import com.cleancode.adapter.out.entities.HeroPackEntity;
import com.cleancode.adapter.out.mapper.HeroPackMapper;
import com.cleancode.adapter.out.repositories.HeroPackRepository;
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
public class FindHeroPackByIdTest {

    @InjectMocks
    private FindHeroPackByIdImpl service;

    @Mock
    private HeroPackRepository heroPackRepository;

    @Captor
    private ArgumentCaptor<Long> heroPackIdCaptor;

    @Test
    public void find_hero_pack_by_id() {
        final var heroPackId = 1L;

        final var expectedHeroPackEntity = new HeroPackEntity();
        expectedHeroPackEntity.setId(heroPackId);

        final var expectedHeroPack = HeroPackMapper.get().toDomain(expectedHeroPackEntity);

        when(heroPackRepository.findById(eq(heroPackId))).thenReturn(Optional.of(expectedHeroPackEntity));

        final var actual = service.findHeroPackById(heroPackId);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroPack);

        verify(heroPackRepository).findById(heroPackIdCaptor.capture());
        assertThat(heroPackIdCaptor.getValue()).isEqualTo(heroPackId);

        verifyNoMoreInteractions(heroPackRepository);
    }

}
