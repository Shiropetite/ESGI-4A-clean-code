package com.cleancode.adapter.out.repositories;

import com.cleancode.adapter.out.entities.HeroRefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRefRepository extends JpaRepository<HeroRefEntity, Long> {

}
