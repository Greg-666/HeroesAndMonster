package com.example.demoex.correctionexorecap.api.controller;

import com.example.demoex.correctionexorecap.api.dto.dto.TypeDTO;
import com.example.demoex.correctionexorecap.api.dto.form.TypeCreateForm;
import com.example.demoex.correctionexorecap.bll.service.impl.TypeServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type")
@RequiredArgsConstructor
public class TypeController {
    private final TypeServiceImpl typeService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid TypeCreateForm form) {
        return ResponseEntity.ok(typeService.create(form.toEntity()));
    }

    @GetMapping
    public ResponseEntity<List<TypeDTO>> getAll() {
        return ResponseEntity.ok(
                typeService.findAll().stream()
                        .map(TypeDTO::fromEntity)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(TypeDTO.fromEntity(typeService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid TypeCreateForm form) {
        typeService.update(id, form.toEntity());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        typeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
