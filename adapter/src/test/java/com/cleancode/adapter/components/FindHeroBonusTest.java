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
public class FindHeroBonusTest {

    @InjectMocks
    private FindHeroBonusImpl service;

    @Mock
    private HeroBonusRepository heroBonusRepository;

    @Captor
    private ArgumentCaptor<String> strongNameCaptor;

    @Captor
    private ArgumentCaptor<String> weakNameCaptor;

    @Test
    public void find_hero_bonus() {
        final var expectedHeroRefEntity = new HeroBonusEntity();

        final var strongName = "Tank";
        final var weakName = "Mage";

        final var expectedHeroRef = HeroBonusMapper.get().toDomain(expectedHeroRefEntity);

        when(heroBonusRepository.findHeroBonusEntityByStrongAndWeak(eq(strongName), eq(weakName)))
                .thenReturn(Optional.of(expectedHeroRefEntity));

        final var actual = service.findHeroBonus("Tank", "Mage");

        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroRef);

        verify(heroBonusRepository).findHeroBonusEntityByStrongAndWeak(strongNameCaptor.capture(), weakNameCaptor.capture());
        assertThat(strongNameCaptor.getValue()).isEqualTo(strongName);
        assertThat(weakNameCaptor.getValue()).isEqualTo(weakName);

        verifyNoMoreInteractions(heroBonusRepository);
    }

}
