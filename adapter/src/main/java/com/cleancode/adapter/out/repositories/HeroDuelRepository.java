package com.cleancode.adapter.out.repositories;

import com.cleancode.adapter.out.entities.HeroDuelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroDuelRepository extends JpaRepository<HeroDuelEntity, Long> {

}
