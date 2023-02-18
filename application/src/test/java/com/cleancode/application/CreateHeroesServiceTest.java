package com.cleancode.application;

import com.cleancode.application.ports.out.CreateHeroesPersistence;
import com.cleancode.application.services.CreateHeroesServiceImpl;
import com.cleancode.domain.HeroRef;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateHeroesServiceTest {

    @InjectMocks
    private CreateHeroesServiceImpl service;

    @Mock
    private CreateHeroesPersistence persistence;

    @Captor
    private ArgumentCaptor<List<HeroRef>> heroesCaptor;

    @Test
    void should_create_new_heroes_ref() {
        final var expectedHeroes = List.of(
                new HeroRef("Tank", 2000, 200, 20, "Commun")
        );

        when(persistence.create(eq(expectedHeroes))).thenReturn(expectedHeroes);

        final var actual = service.create(expectedHeroes);

        assertThat(actual)
            .usingRecursiveComparison()
            .isEqualTo(expectedHeroes);

        verify(persistence).create(heroesCaptor.capture());

        assertThat(heroesCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(expectedHeroes);

        verifyNoMoreInteractions(persistence);
    }

}
