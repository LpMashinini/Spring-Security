package com.example.security.controller;

import com.example.security.domain.entity.Room;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @PostMapping
    public String addRoom(){
        return "Room added";
    }

    @GetMapping("/{id}")
    @PostAuthorize("returnObject.assignedTo == authentication.name")
    public Room getRoomById(@PathVariable Long id){
        return new Room(id,"Lebogang");
    }

    @GetMapping
    public String getRooms(){
        return "all rooms";
    }
}
