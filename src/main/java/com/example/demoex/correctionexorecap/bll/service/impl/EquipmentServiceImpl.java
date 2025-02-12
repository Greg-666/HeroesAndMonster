package com.example.demoex.correctionexorecap.bll.service.impl;

import com.example.demoex.correctionexorecap.bll.exception.ressourceNotFound.RessourceNotFoundException;
import com.example.demoex.correctionexorecap.bll.service.EquipmentService;
import com.example.demoex.correctionexorecap.bll.service.HeroService;
import com.example.demoex.correctionexorecap.dal.repository.EquipmentRepository;
import com.example.demoex.correctionexorecap.domain.Equipment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final HeroService heroService;

    @Override
    public Long create(Equipment equipment) {
        // On vérifie si le héo existe
        heroService.findById(equipment.getHero().getId());

        return equipmentRepository.save(equipment).getId();
    }

    @Override
    public Equipment findById(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Équipement non trouvé avec l'id: " + id));
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public List<Equipment> findByHeroId(Long heroId) {
        // Est ce que le héro existe ?
        heroService.findById(heroId);

        return equipmentRepository.findByHeroId(heroId);
    }

    @Override
    public void update(Long id, Equipment equipment) {
        Equipment equipmentToUpdate = findById(id);

        // on check si le héro existe
        if(!equipmentToUpdate.getHero().getId().equals(equipment.getHero().getId())) {
            heroService.findById(equipment.getHero().getId());
        }

        equipmentToUpdate.setDescription(equipment.getDescription());
        equipmentToUpdate.setHero(equipment.getHero());

        equipmentRepository.save(equipmentToUpdate);
    }

    @Override
    public void delete(Long id) {
        if(!equipmentRepository.existsById(id)) {
            throw new RessourceNotFoundException("Équipement non trouvé avec l'id: " + id);
        }
        equipmentRepository.deleteById(id);
    }
}
