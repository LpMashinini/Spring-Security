package com.example.security.controller;

import com.example.security.domain.entity.Room;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @PostMapping
    @PostAuthorize("hasAuthority('ROOM_ADD')")
    public String addRoom(){
        return "Room added";
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAuthority('ROOM_VIEW')")
    public Room getRoomById(@PathVariable Long id){
        return new Room(id,"Lebogang");
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROOM_VIEW_ALL')")
    public String getRooms(){
        return "all rooms";
    }
}
