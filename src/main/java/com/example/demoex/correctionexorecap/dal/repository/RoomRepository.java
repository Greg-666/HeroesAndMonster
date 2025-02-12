package com.example.demoex.correctionexorecap.dal.repository;

import com.example.demoex.correctionexorecap.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT COUNT(m) FROM Monster m WHERE m.room.id = :roomId")
    int countMonstersByRoomId(Long roomId);

    @Query("SELECT COUNT(h) FROM Hero h WHERE h.room.id = :roomId")
    int countHeroesByRoomId(Long roomId);
}
