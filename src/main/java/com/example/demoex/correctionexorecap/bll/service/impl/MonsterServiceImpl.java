package com.example.demoex.correctionexorecap.bll.service.impl;

import com.example.demoex.correctionexorecap.bll.exception.ressourceAlreadyExists.RessourceAlreadyExistsException;
import com.example.demoex.correctionexorecap.bll.exception.ressourceNotFound.RessourceNotFoundException;
import com.example.demoex.correctionexorecap.bll.exception.roomFull.RoomFullException;
import com.example.demoex.correctionexorecap.bll.service.MonsterService;
import com.example.demoex.correctionexorecap.bll.service.RoomService;
import com.example.demoex.correctionexorecap.dal.repository.MonsterRepository;
import com.example.demoex.correctionexorecap.domain.Monster;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonsterServiceImpl implements MonsterService {

    private final MonsterRepository monsterRepository;
    private final RoomService roomService;

    @Override
    public Long create(Monster monster) {
        // Est ce que ma pièce à assez de place
        if(!roomService.hasAvailableSpace(monster.getRoom().getId(), 1)) {
            throw new RoomFullException("La salle est pleine, impossible d'ajouter un monstre");
        }

        // On vient vérifier si un monstre portant le même nom existe déjà
        if(monsterRepository.existsByNameAndRoomId(monster.getName(), monster.getRoom().getId())) {
            throw new RessourceAlreadyExistsException(
                    "Un monstre avec le nom '" + monster.getName() +
                            "' existe déjà dans la salle " + monster.getRoom().getId()
            );
        }

        return monsterRepository.save(monster).getId();
    }

    @Override
    public Monster findById(Long id) {
        return monsterRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Monstre non trouvé avec l'id: " + id));
    }

    @Override
    public List<Monster> findAll() {
        return monsterRepository.findAll();
    }

    @Override
    public List<Monster> findByRoomId(Long roomId) {
        return monsterRepository.findByRoomId(roomId);
    }

    @Override
    public void update(Long id, Monster monster) {
        Monster monsterToUpdate = findById(id);

        // si jamais on change le monstre de salle
        if(!monsterToUpdate.getRoom().getId().equals(monster.getRoom().getId())) {
            // check s'il y a de la place
            if(!roomService.hasAvailableSpace(monster.getRoom().getId(), 1)) {
                throw new RoomFullException("La nouvelle salle est pleine, impossible de déplacer le monstre");
            }
        }

        // check si le nouveau nom n'est pas déjà présent
        if(!monsterToUpdate.getName().equals(monster.getName())
                && monsterRepository.existsByNameAndRoomId(monster.getName(), monster.getRoom().getId())) {
            throw new RessourceAlreadyExistsException(
                    "Un monstre avec le nom '" + monster.getName() +
                            "' existe déjà dans la salle " + monster.getRoom().getId()
            );
        }

        monsterToUpdate.setName(monster.getName());
        monsterToUpdate.setRegime(monster.getRegime());
        monsterToUpdate.setRoom(monster.getRoom());
        monsterToUpdate.setTypes(monster.getTypes());

        monsterRepository.save(monsterToUpdate);
    }

    @Override
    public void delete(Long id) {
        if(!monsterRepository.existsById(id)) {
            throw new RessourceNotFoundException("Monstre non trouvé avec l'id: " + id);
        }
        monsterRepository.deleteById(id);
    }
}
