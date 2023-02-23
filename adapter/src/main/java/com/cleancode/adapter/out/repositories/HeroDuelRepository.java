package com.cleancode.adapter.out.repositories;

import com.cleancode.adapter.out.entities.HeroDuelEntity;
import com.cleancode.adapter.out.entities.HeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroDuelRepository extends JpaRepository<HeroDuelEntity, Long> {

    List<HeroDuelEntity> findByWinner(HeroEntity winner);

    List<HeroDuelEntity> findByLoser(HeroEntity loser);

}
