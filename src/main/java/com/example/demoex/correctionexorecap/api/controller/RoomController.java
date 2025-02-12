package com.example.demoex.correctionexorecap.api.controller;

import com.example.demoex.correctionexorecap.api.dto.dto.RoomDTO;
import com.example.demoex.correctionexorecap.api.dto.form.RoomCreateForm;
import com.example.demoex.correctionexorecap.bll.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid RoomCreateForm form) {
        return ResponseEntity.ok(roomService.create(form.toEntity()));
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAll() {
        return ResponseEntity.ok(
                roomService.findAll().stream()
                        .map(RoomDTO::fromEntity)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(RoomDTO.fromEntity(roomService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid RoomCreateForm form) {
        roomService.update(id, form.toEntity());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
