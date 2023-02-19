package com.cleancode.adapter.in;

import com.cleancode.application.ports.in.CreatePlayerService;
import com.cleancode.domain.Player;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity createPlayer(@RequestBody CreatePlayerRequestBody player) {
        try {
            var createdPlayer = this.service.create(player.name);
            return ResponseEntity.ok().body(createdPlayer);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
