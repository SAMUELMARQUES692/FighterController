package dev.samuel.FightController.controller;

import dev.samuel.FightController.request.FighterRequest;
import dev.samuel.FightController.response.FighterResponse;
import dev.samuel.FightController.service.FighterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<FighterResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(fighterService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FighterResponse> uptade(@RequestBody FighterRequest request, @PathVariable UUID id) {
        return ResponseEntity.ok(fighterService.uptade(request, id));
    }

    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        fighterService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
