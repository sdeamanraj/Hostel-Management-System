package com.hostel.management.room.service;

import com.hostel.management.room.entity.Room;
import com.hostel.management.room.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room assignStudentToRoom(String roomNumber) {
        Optional<Room> roomOptional = roomRepository.findByRoomNumber(roomNumber);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            if (room.getAssignedStudents() < room.getCapacity()) {
                room.setAssignedStudents(room.getAssignedStudents() + 1);
                room.setAvailability(room.getAssignedStudents() < room.getCapacity());
                return roomRepository.save(room);
            } else {
                throw new RuntimeException("Room is already full!");
            }
        }
        throw new RuntimeException("Room not found!");
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room getRoomByNumber(String roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber).orElseThrow(() -> new RuntimeException("Room not found!"));
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }
}