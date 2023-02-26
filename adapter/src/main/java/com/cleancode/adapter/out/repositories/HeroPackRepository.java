package com.cleancode.adapter.out.repositories;

import com.cleancode.adapter.out.entities.HeroPackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroPackRepository extends JpaRepository<HeroPackEntity, Long> {}
