package com.example.demoex.correctionexorecap.api.controller;

import com.example.demoex.correctionexorecap.api.dto.dto.MonsterDTO;
import com.example.demoex.correctionexorecap.api.dto.form.MonsterCreateForm;
import com.example.demoex.correctionexorecap.bll.exception.ressourceNotFound.RessourceNotFoundException;
import com.example.demoex.correctionexorecap.bll.service.MonsterService;
import com.example.demoex.correctionexorecap.bll.service.RoomService;
import com.example.demoex.correctionexorecap.bll.service.TypeService;
import com.example.demoex.correctionexorecap.domain.Monster;
import com.example.demoex.correctionexorecap.domain.Room;
import com.example.demoex.correctionexorecap.domain.Type;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/monsters")
@RequiredArgsConstructor
public class MonsterController {

    private final MonsterService monsterService;
    private final RoomService roomService;
    private final TypeService typeService;


    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid MonsterCreateForm form) {
        // On récupère la pièce
        Room room = roomService.findById(form.roomId());

        // On s'occupe de ses types
        Set<Type> types = typeService.findAllByIds(form.typeIds());
        if(types.size() != form.typeIds().size()) {
            throw new RessourceNotFoundException("Certains types n'ont pas été trouvés");
        }

        Monster monster = form.toEntity(room, types);
        return ResponseEntity.ok(monsterService.create(monster));
    }

    @GetMapping
    public ResponseEntity<List<MonsterDTO>> getAll() {
        return ResponseEntity.ok(
                monsterService.findAll().stream()
                        .map(MonsterDTO::fromEntity)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonsterDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(MonsterDTO.fromEntity(monsterService.findById(id)));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<MonsterDTO>> getByRoomId(@PathVariable Long roomId) {
        return ResponseEntity.ok(
                monsterService.findByRoomId(roomId).stream()
                        .map(MonsterDTO::fromEntity)
                        .toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid MonsterCreateForm form) {
        Room room = roomService.findById(form.roomId());
        Set<Type> types = typeService.findAllByIds(form.typeIds());
        if(types.size() != form.typeIds().size()) {
            throw new RessourceNotFoundException("Certains types n'ont pas été trouvés");
        }
        Monster monster = form.toEntity(room, types);
        monsterService.update(id, monster);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        monsterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
