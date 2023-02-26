package com.cleancode.adapter.services;

import com.cleancode.adapter.out.services.CreateHeroesPersistenceImpl;
import com.cleancode.application.ports.out.repositories.CreateHeroRef;
import com.cleancode.domain.HeroRef;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class CreateHeroesPersistenceTest {

    @InjectMocks
    private CreateHeroesPersistenceImpl persistence;

    @Mock
    private CreateHeroRef createHeroRef;

    @Captor
    private ArgumentCaptor<HeroRef> heroRefArgumentCaptor;

    @Test
    void should_create_hero_ref() {
        final var expectedHeroRef = new HeroRef(1L, "", 0, 0, 0, "");

        when(createHeroRef.createHeroRef(eq(expectedHeroRef))).thenReturn(expectedHeroRef);

        final var actual = persistence.createHeroRef(expectedHeroRef);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroRef);

        verify(createHeroRef).createHeroRef(heroRefArgumentCaptor.capture());
        assertThat(heroRefArgumentCaptor.getValue()).isEqualTo(expectedHeroRef);

        verifyNoMoreInteractions(createHeroRef);
    }

}
