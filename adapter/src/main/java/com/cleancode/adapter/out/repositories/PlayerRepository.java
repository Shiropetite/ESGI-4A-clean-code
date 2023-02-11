package com.cleancode.adapter.out.repositories;

import com.cleancode.adapter.out.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

}
