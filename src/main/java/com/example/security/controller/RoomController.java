package com.example.security.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @PostMapping
    public String addRoom(){
        return "Room added";
    }

    @GetMapping("/{id}")
    public String getRoomById(@PathVariable Long id){
        return "Room fecthed for id: " + id;
    }

    @GetMapping
    public String getRooms(){
        return "all rooms";
    }
}
