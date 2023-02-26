package com.cleancode.adapter.services;

import com.cleancode.adapter.out.services.SearchAvailableHeroesPersistenceImpl;
import com.cleancode.application.ports.out.repositories.FindAllHeroRef;
import com.cleancode.domain.HeroRef;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class SearchAvailableHeroesPersistenceTest {

    @InjectMocks
    private SearchAvailableHeroesPersistenceImpl persistence;

    @Mock
    private FindAllHeroRef findAllHeroRef;

    @Test
    void should_find_all_hero_refs() {
        final var expectedHeroRefs = List.of(
            new HeroRef(1L, "", 0, 0, 0, "")
        );

        when(findAllHeroRef.findAllHeroRef()).thenReturn(expectedHeroRefs);

        final var actual = persistence.findAllHeroRef();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroRefs);

        verifyNoMoreInteractions(findAllHeroRef);
    }

}
