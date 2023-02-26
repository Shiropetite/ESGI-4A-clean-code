package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.FindHeroBonusImpl;
import com.cleancode.adapter.out.entities.HeroBonusEntity;
import com.cleancode.adapter.out.mapper.HeroBonusMapper;
import com.cleancode.adapter.out.repositories.HeroBonusRepository;
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
public final class FindHeroBonusTest {

    @InjectMocks
    private FindHeroBonusImpl service;

    @Mock
    private HeroBonusRepository heroBonusRepository;

    @Captor
    private ArgumentCaptor<String> strongNameCaptor;

    @Captor
    private ArgumentCaptor<String> weakNameCaptor;

    @Test
    void should_find_hero_bonus() {
        final var mockHeroRefEntity = new HeroBonusEntity();
        final var mockStrongHeroName = "mockStrongHeroName";
        final var mockWeakHeroName = "mockWeakHeroName";
        final var expectedHeroRef = HeroBonusMapper.get().toDomain(mockHeroRefEntity);

        when(heroBonusRepository.findHeroBonusEntityByStrongAndWeak(eq(mockStrongHeroName), eq(mockWeakHeroName)))
            .thenReturn(Optional.of(mockHeroRefEntity));

        final var actual = service.findHeroBonus(mockStrongHeroName, mockWeakHeroName);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroRef);

        verify(heroBonusRepository).findHeroBonusEntityByStrongAndWeak(strongNameCaptor.capture(), weakNameCaptor.capture());
        assertThat(strongNameCaptor.getValue()).isEqualTo(mockStrongHeroName);
        assertThat(weakNameCaptor.getValue()).isEqualTo(mockWeakHeroName);

        verifyNoMoreInteractions(heroBonusRepository);
    }

}
