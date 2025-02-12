package com.example.demoex.correctionexorecap.api.controller;

import com.example.demoex.correctionexorecap.api.dto.dto.EquipmentDTO;
import com.example.demoex.correctionexorecap.api.dto.form.EquipmentCreateForm;
import com.example.demoex.correctionexorecap.bll.service.EquipmentService;
import com.example.demoex.correctionexorecap.bll.service.HeroService;
import com.example.demoex.correctionexorecap.domain.Equipment;
import com.example.demoex.correctionexorecap.domain.Hero;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipments")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;
    private final HeroService heroService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid EquipmentCreateForm form) {
        Hero hero = heroService.findById(form.heroId());
        Equipment equipment = form.toEntity(hero);
        return ResponseEntity.ok(equipmentService.create(equipment));
    }

    @GetMapping
    public ResponseEntity<List<EquipmentDTO>> getAll() {
        return ResponseEntity.ok(
                equipmentService.findAll().stream()
                        .map(EquipmentDTO::fromEntity)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(EquipmentDTO.fromEntity(equipmentService.findById(id)));
    }

    @GetMapping("/hero/{heroId}")
    public ResponseEntity<List<EquipmentDTO>> getByHeroId(@PathVariable Long heroId) {
        return ResponseEntity.ok(
                equipmentService.findByHeroId(heroId).stream()
                        .map(EquipmentDTO::fromEntity)
                        .toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid EquipmentCreateForm form) {
        Hero hero = heroService.findById(form.heroId());
        Equipment equipment = form.toEntity(hero);
        equipmentService.update(id, equipment);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        equipmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
