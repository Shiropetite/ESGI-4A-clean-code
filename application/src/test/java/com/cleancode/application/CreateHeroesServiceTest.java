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
public final class CreateHeroesServiceTest {

    @InjectMocks
    private CreateHeroesServiceImpl service;

    @Mock
    private CreateHeroesPersistence persistence;

    @Captor
    private ArgumentCaptor<HeroRef> refsCaptor;

    @Test
    void should_create_new_heroes_ref() {
        final var mockRef1 = new HeroRef(
            1L,
            "Tank",
            2000,
            200,
            20,
            "Commun"
        );
        final var expectedRefs = List.of(
            mockRef1
        );

        when(persistence.createHeroRef(eq(mockRef1))).thenReturn(mockRef1);

        final var actual = service.create(expectedRefs);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedRefs);

        verify(persistence).createHeroRef(refsCaptor.capture());
        assertThat(refsCaptor.getValue()).usingRecursiveComparison().isEqualTo(mockRef1);

        verifyNoMoreInteractions(persistence);
    }

}
