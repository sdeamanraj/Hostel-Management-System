package com.hostel.management.room.controller;

import com.hostel.management.room.entity.Room;
import com.hostel.management.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @PutMapping("/assignStudent")
    public Room assignStudentToRoom(@RequestParam String roomNumber) {
        return roomService.assignStudentToRoom(roomNumber);
    }

    @GetMapping("/{roomNumber}")
    public Room getRoomByNumber(@PathVariable String roomNumber) {
        return roomService.getRoomByNumber(roomNumber);
    }

    @GetMapping
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }
}