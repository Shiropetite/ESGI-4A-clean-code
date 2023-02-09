package com.cleancode.adapter.out.repositories;

import com.cleancode.adapter.out.entities.RefHeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefHeroRepository extends JpaRepository<RefHeroEntity, Long> {

}
