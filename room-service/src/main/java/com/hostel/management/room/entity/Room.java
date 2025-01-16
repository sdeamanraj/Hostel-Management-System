package com.hostel.management.room.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomNumber;
    private int capacity;
    private int assignedStudents; // Tracks the number of assigned students
    private boolean availability;
    private String hostelName;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAssignedStudents() {
        return assignedStudents;
    }

    public void setAssignedStudents(int assignedStudents) {
        this.assignedStudents = assignedStudents;
    }

    public boolean isAvailability() {
        return assignedStudents < capacity;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }
}