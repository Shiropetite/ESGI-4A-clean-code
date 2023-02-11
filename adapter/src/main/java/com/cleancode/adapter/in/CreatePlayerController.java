package com.cleancode.adapter.in;

import com.cleancode.application.ports.in.CreatePlayerService;
import com.cleancode.domain.Player;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class CreatePlayerController {

    private final CreatePlayerService service;

    public CreatePlayerController(CreatePlayerService service) { this.service = service; }

    @PostMapping
    public Player createPlayer(@RequestBody CreatePlayerRequestBody player) {
        return this.service.create(player.name);
    }

}
