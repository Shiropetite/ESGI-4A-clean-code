package com.cleancode.adapter.out.repositories;

import com.cleancode.adapter.out.entities.HeroDuelEntity;
import com.cleancode.adapter.out.entities.HeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroDuelRepository extends JpaRepository<HeroDuelEntity, Long> {

    Optional<List<HeroDuelEntity>> findHeroDuelEntitiesByWinner(HeroEntity winner);

    Optional<List<HeroDuelEntity>> findHeroDuelEntitiesByLoser(HeroEntity loser);

}
