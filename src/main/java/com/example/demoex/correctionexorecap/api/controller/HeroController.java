package com.example.demoex.correctionexorecap.api.controller;

import com.example.demoex.correctionexorecap.api.dto.dto.HeroDTO;
import com.example.demoex.correctionexorecap.api.dto.form.HeroCreateForm;
import com.example.demoex.correctionexorecap.bll.service.HeroService;
import com.example.demoex.correctionexorecap.bll.service.RoomService;
import com.example.demoex.correctionexorecap.domain.Hero;
import com.example.demoex.correctionexorecap.domain.Room;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/heroes")
@RequiredArgsConstructor
public class HeroController {
    private final HeroService heroService;
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid HeroCreateForm form) {
        // Comme le héro peut-être fait sans pièce on peut le mettre à null
        Room room = null;
        if(form.roomId() != null) {
            room = roomService.findById(form.roomId());
        }

        Hero hero = form.toEntity(room);
        return ResponseEntity.ok(heroService.create(hero));
    }

    @GetMapping
    public ResponseEntity<List<HeroDTO>> getAll() {
        return ResponseEntity.ok(
                heroService.findAll().stream()
                        .map(HeroDTO::fromEntity)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(HeroDTO.fromEntity(heroService.findById(id)));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<HeroDTO>> getByRoomId(@PathVariable Long  roomId) {
        return ResponseEntity.ok(
                heroService.findByRoomId( roomId).stream()
                        .map(HeroDTO::fromEntity)
                        .toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid HeroCreateForm form) {
        Room room = null;
        if(form.roomId() != null) {
            room = roomService.findById(form.roomId());
        }

        Hero hero = form.toEntity(room);
        heroService.update(id, hero);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        heroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
