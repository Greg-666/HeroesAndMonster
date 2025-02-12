package com.example.demoex.correctionexorecap.bll.service.impl;

import com.example.demoex.correctionexorecap.bll.exception.ressourceNotFound.RessourceNotFoundException;
import com.example.demoex.correctionexorecap.bll.exception.roomFull.RoomFullException;
import com.example.demoex.correctionexorecap.bll.service.RoomService;
import com.example.demoex.correctionexorecap.dal.repository.HeroRepository;
import com.example.demoex.correctionexorecap.dal.repository.MonsterRepository;
import com.example.demoex.correctionexorecap.dal.repository.RoomRepository;
import com.example.demoex.correctionexorecap.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final MonsterRepository monsterRepository;
    private final HeroRepository heroRepository;

    @Override
    public Long create(Room room) {
        if(room.getOccupancy() <= 0) {
            throw new RessourceNotFoundException("La capacité d'une salle doit être supérieure à 0");
        }
        return roomRepository.save(room).getId();
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Salle non trouvée avec l'id: " + id));
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public void update(Long id, Room room) {
        Room roomToUpdate = findById(id);

        // On vient check si la capacité de la pièce est ok (vu qu'on est dans un uptade)
        int currentOccupants = roomRepository.countMonstersByRoomId(id) +
                roomRepository.countHeroesByRoomId(id);

        if(room.getOccupancy() < currentOccupants) {
            throw new RoomFullException("La nouvelle capacité est trop petite pour les occupants actuels");
        }

        roomToUpdate.setOccupancy(room.getOccupancy());
        roomToUpdate.setDescription(room.getDescription());
        roomRepository.save(roomToUpdate);
    }

    @Override
    public void delete(Long id) {
        if(!roomRepository.existsById(id)) {
            throw new RessourceNotFoundException("Salle non trouvée avec l'id: " + id);
        }
        roomRepository.deleteById(id);
    }

    @Override
    public boolean hasAvailableSpace(Long roomId, int requiredSpace) {
        Room room = findById(roomId);
        int currentOccupants = roomRepository.countMonstersByRoomId(roomId) +
                roomRepository.countHeroesByRoomId(roomId);

        return (room.getOccupancy() - currentOccupants) >= requiredSpace;
    }

}
