package dev.samuel.FightController.controller;

import dev.samuel.FightController.request.FighterRequest;
import dev.samuel.FightController.response.FighterResponse;
import dev.samuel.FightController.service.FighterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/fighters")
public class FighterController {

    private final FighterService fighterService;

    @PostMapping
    public ResponseEntity<FighterResponse> create(@RequestBody FighterRequest request) {
       return ResponseEntity.ok(fighterService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<FighterResponse>> findAll() {
        return ResponseEntity.ok(fighterService.findAll());
    }

}
