package com.cleancode.adapter.out.repositories;

import com.cleancode.adapter.out.entities.HeroBonusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroBonusRepository extends JpaRepository<HeroBonusEntity, Long> {

    Optional<HeroBonusEntity> findHeroBonusEntityByStrongAndWeak(String strong, String weak);

}
