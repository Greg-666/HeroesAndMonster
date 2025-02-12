package com.example.demoex.correctionexorecap.bll.service.impl;

import com.example.demoex.correctionexorecap.bll.exception.ressourceAlreadyExists.RessourceAlreadyExistsException;
import com.example.demoex.correctionexorecap.bll.exception.ressourceNotFound.RessourceNotFoundException;
import com.example.demoex.correctionexorecap.bll.exception.roomFull.RoomFullException;
import com.example.demoex.correctionexorecap.bll.service.HeroService;
import com.example.demoex.correctionexorecap.bll.service.RoomService;
import com.example.demoex.correctionexorecap.dal.repository.HeroRepository;
import com.example.demoex.correctionexorecap.domain.Hero;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {
    private final HeroRepository heroRepository;
    private final RoomService roomService;

    @Override
    public Long create(Hero hero) {
        // On peut créer un héro sans lui ajouter une salle
        if(hero.getRoom() != null) {
            // la salle à la capacité de prendre un héro ?
            if(!roomService.hasAvailableSpace(hero.getRoom().getId(), 1)) {
                throw new RoomFullException("La salle est pleine, impossible d'ajouter un héros");
            }

            // On vient vérifier si un héro avec le même nom existe déjà dans la salle
            if(heroRepository.existsByNameAndRoomId(hero.getName(), hero.getRoom().getId())) {
                throw new RessourceAlreadyExistsException(
                        "Un héros avec le nom '" + hero.getName() +
                                "' existe déjà dans la salle " + hero.getRoom().getId()
                );
            }
        }

        return heroRepository.save(hero).getId();
    }

    @Override
    public Hero findById(Long id) {
        return heroRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Héros non trouvé avec l'id: " + id));
    }

    @Override
    public List<Hero> findAll() {
        return heroRepository.findAll();
    }

    @Override
    public List<Hero> findByRoomId(Long roomId) {
        return heroRepository.findByRoomId(roomId);
    }

    @Override
    public void update(Long id, Hero hero) {
        Hero heroToUpdate = findById(id);

        // region changement de salle
        Long currentRoomId = heroToUpdate.getRoom() != null ? heroToUpdate.getRoom().getId() : null;
        Long newRoomId = hero.getRoom() != null ? hero.getRoom().getId() : null;

        // si jamais on déplace le héro dans une nouvelle salle
        if(newRoomId != null && !newRoomId.equals(currentRoomId)) {
            // toujours check s'il y a de la place
            if(!roomService.hasAvailableSpace(newRoomId, 1)) {
                throw new RoomFullException("La nouvelle salle est pleine, impossible de déplacer le héros");
            }

            // On vérifie qu'on a pas déjà un héro avec le même nom
            if(heroRepository.existsByNameAndRoomId(hero.getName(), newRoomId)) {
                throw new RessourceAlreadyExistsException(
                        "Un héros avec le nom '" + hero.getName() +
                                "' existe déjà dans la salle " + newRoomId
                );
            }
        }

        heroToUpdate.setName(hero.getName());
        heroToUpdate.setProfession(hero.getProfession());
        heroToUpdate.setRoom(hero.getRoom());

        heroRepository.save(heroToUpdate);
    }

    @Override
    public void delete(Long id) {
        if(!heroRepository.existsById(id)) {
            throw new RessourceNotFoundException("Héros non trouvé avec l'id: " + id);
        }
        heroRepository.deleteById(id);
    }


}
