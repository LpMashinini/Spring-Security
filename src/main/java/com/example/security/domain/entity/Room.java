package com.example.security.domain.entity;

public class Room {
    private Long id;
    private String assigned;

    public Room(Long id, String assigned) {
        this.id = id;
        this.assigned = assigned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }
}
