package com.example.demoex.correctionexorecap.bll.service;

import com.example.demoex.correctionexorecap.domain.Room;

import java.util.List;

public interface RoomService {
    Long create(Room room);
    Room findById(Long id);
    List<Room> findAll();
    void update(Long id, Room room);
    void delete(Long id);
    boolean hasAvailableSpace(Long roomId, int requiredSpace);
}
