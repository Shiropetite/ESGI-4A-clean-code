package com.cleancode.application.ports.out.repositories;

import com.cleancode.domain.Player;

public interface FindPlayerById {

    Player findPlayerById(Long id);

}
