package com.cleancode.application.ports.out;

import com.cleancode.application.ports.out.repositories.CreatePlayer;
import com.cleancode.application.ports.out.repositories.FindPlayerByName;

public interface CreatePlayerPersistence extends
        FindPlayerByName,
        CreatePlayer
{ }
